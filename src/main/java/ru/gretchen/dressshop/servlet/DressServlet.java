package ru.gretchen.dressshop.servlet;

import ru.gretchen.dressshop.controller.Controller;
import ru.gretchen.dressshop.controller.ControllerFactory;
import ru.gretchen.dressshop.model.DressEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DressServlet extends HttpServlet {

    private Controller controller;

    @Override
    public void init() throws ServletException {
        this.controller = ControllerFactory.getController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DressEntity> dresses = controller.getAllDresses();
        req.setAttribute("dresses", dresses);

        String path = "/view/list.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
