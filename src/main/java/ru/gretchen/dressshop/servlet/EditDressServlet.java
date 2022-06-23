package ru.gretchen.dressshop.servlet;

import ru.gretchen.dressshop.controller.Controller;
import ru.gretchen.dressshop.controller.ControllerFactory;
import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.Enumeration.Color;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditDressServlet extends HttpServlet {
    private Controller controller;

    @Override
    public void init() throws ServletException {
        this.controller = ControllerFactory.getController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        DressEntity dress = controller.getDress(id).orElseThrow(() -> new RuntimeException());

        req.setAttribute("dress", dress);

        String path = "/view/dress-form.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        Color color = Color.valueOf(req.getParameter("color"));
        Long price = Long.valueOf(req.getParameter("price"));
        Long inStock = Long.valueOf(req.getParameter("inStock"));

        DressEntity dressEntity = new DressEntity(null, color, price, inStock);
        controller.updateDress(id, dressEntity);

        resp.sendRedirect(getServletContext().getContextPath()  + "/list");
    }

}
