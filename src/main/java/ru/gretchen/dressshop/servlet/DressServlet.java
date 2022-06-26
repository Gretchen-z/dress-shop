package ru.gretchen.dressshop.servlet;

import lombok.SneakyThrows;
import ru.gretchen.dressshop.controller.DressController;
import ru.gretchen.dressshop.controller.DressControllerFactory;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.Enumeration.Color;
import ru.gretchen.dressshop.model.HttpRequestResponseWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public class DressServlet extends HttpServlet {

    private DressController controller;
    private Map<String, Map<String, BiConsumer<DressServlet, HttpRequestResponseWrapper>>> handlingMap;

    @Override
    public void init() throws ServletException {
        this.controller = DressControllerFactory.getController();
        handlingMap = new HashMap<>();

        Map<String, BiConsumer<DressServlet, HttpRequestResponseWrapper>> getMap = new HashMap<>();
        Map<String, BiConsumer<DressServlet, HttpRequestResponseWrapper>> postMap = new HashMap<>();

        getMap.put("/list", DressServlet::getList);
        getMap.put("/edit", DressServlet::getDressForm);
        getMap.put("/add", DressServlet::getDressForm);
        getMap.put("/delete", DressServlet::deleteDress);
        postMap.put("/edit", DressServlet::updateDress);
        postMap.put("/add", DressServlet::addDress);

        handlingMap.put("GET", getMap);
        handlingMap.put("POST", postMap);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        String pathInfo = req.getPathInfo();

        handlingMap.get(method)
                .get(pathInfo)
                .accept(this, new HttpRequestResponseWrapper(req, resp));
    }

    @SneakyThrows
    private void getList(HttpRequestResponseWrapper wrapper) {
        List<DressEntity> dresses = controller.getAllDresses();
        wrapper.getRequest().setAttribute("dresses", dresses);

        String path = "/view/list.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(wrapper.getRequest(), wrapper.getResponse());
    }

    @SneakyThrows
    private void getDressForm(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        HttpServletResponse resp = wrapper.getResponse();

        String stringId = req.getParameter("id");

        if (Objects.nonNull(stringId)) {
            Long id = Long.valueOf(req.getParameter("id"));
            DressEntity dress = controller.getDress(id);

            req.setAttribute("dress", dress);
        }

        String path = "/view/dress-form.jsp";
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @SneakyThrows
    private void updateDress(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();

        Long id = Long.valueOf(req.getParameter("id"));

        DressEntity dressEntity = dressFromRequest(req);
        controller.updateDress(id, dressEntity);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void addDress(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        DressEntity dressEntity = dressFromRequest(req);
        controller.createDress(dressEntity);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void deleteDress(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        Long id = Long.valueOf(req.getParameter("id"));

        controller.deleteDress(id);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    private DressEntity dressFromRequest(HttpServletRequest req) {
        Color color = Color.valueOf(req.getParameter("color"));
        Long price = Long.valueOf(req.getParameter("price"));
        Long inStock = Long.valueOf(req.getParameter("inStock"));

        return new DressEntity(color, price, inStock);
    }
}
