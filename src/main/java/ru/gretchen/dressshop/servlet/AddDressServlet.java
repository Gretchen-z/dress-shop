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

public class AddDressServlet extends HttpServlet {
    private Controller controller;

    @Override
    public void init() throws ServletException {
        this.controller = ControllerFactory.getController();
    }

    //Получение странички с формой добавления
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/view/dress-form.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    //Обработка переданных данных
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Color color = Color.valueOf(req.getParameter("color"));
        Long price = Long.valueOf(req.getParameter("price"));
        Long inStock = Long.valueOf(req.getParameter("inStock"));

        DressEntity dressEntity = new DressEntity(null, color, price, inStock);
        controller.createDress(dressEntity);

        resp.sendRedirect(getServletContext().getContextPath()  + "/list");
    }
}
