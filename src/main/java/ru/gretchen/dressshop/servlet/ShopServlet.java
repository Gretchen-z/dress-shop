package ru.gretchen.dressshop.servlet;

import lombok.SneakyThrows;
import ru.gretchen.dressshop.controller.ShopController;
import ru.gretchen.dressshop.controller.ShopControllerFactory;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.HttpRequestResponseWrapper;
import ru.gretchen.dressshop.model.ShopEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

public class ShopServlet extends HttpServlet {

    private ShopController controller;
    private Map<String, Map<String, BiConsumer<ShopServlet, HttpRequestResponseWrapper>>> handlingMap;

    @Override
    public void init() throws ServletException {
        this.controller = ShopControllerFactory.getController();
        handlingMap = new HashMap<>();

        Map<String, BiConsumer<ShopServlet, HttpRequestResponseWrapper>> getMap = new HashMap<>();
        Map<String, BiConsumer<ShopServlet, HttpRequestResponseWrapper>> postMap = new HashMap<>();

        getMap.put("/list", ShopServlet::getList);
        getMap.put("/edit", ShopServlet::getShopForm);
        getMap.put("/add", ShopServlet::getShopForm);
        getMap.put("/delete", ShopServlet::deleteShop);
        getMap.put("/lazyinit", ShopServlet::lazyInitExample);
        postMap.put("/edit", ShopServlet::updateShop);
        postMap.put("/add", ShopServlet::addShop);

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
    private void lazyInitExample(HttpRequestResponseWrapper wrapper) {
        Set<DressEntity> dresses = controller.getDresses(1L);
    }

    @SneakyThrows
    private void getList(HttpRequestResponseWrapper wrapper) {
        List<ShopEntity> shops = controller.getAllShops();
        wrapper.getRequest().setAttribute("shops", shops);

        String path = "/view/shop-list.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(wrapper.getRequest(), wrapper.getResponse());
    }

    @SneakyThrows
    private void getShopForm(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        HttpServletResponse resp = wrapper.getResponse();

        String stringId = req.getParameter("id");

        if (Objects.nonNull(stringId)) {
            Long id = Long.valueOf(req.getParameter("id"));
            ShopEntity shop = controller.getShop(id);

            req.setAttribute("shop", shop);
        }

        String path = "/view/shop-form.jsp";
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @SneakyThrows
    private void updateShop(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();

        Long id = Long.valueOf(req.getParameter("id"));

        ShopEntity shop = shopFromRequest(req);
        controller.updateShop(id, shop);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void addShop(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        ShopEntity shop = shopFromRequest(req);
        controller.createShop(shop);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void deleteShop(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        Long id = Long.valueOf(req.getParameter("id"));

        controller.deleteShop(id);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    private ShopEntity shopFromRequest(HttpServletRequest req) {
        String address = String.valueOf(req.getParameter("address"));

        return new ShopEntity(address);
    }
}
