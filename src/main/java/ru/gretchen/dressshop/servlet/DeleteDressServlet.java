package ru.gretchen.dressshop.servlet;

import ru.gretchen.dressshop.controller.Controller;
import ru.gretchen.dressshop.controller.ControllerFactory;
import ru.gretchen.dressshop.model.DressEntity;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteDressServlet extends HttpServlet {

    private Controller controller;

    @Override
    public void init() throws ServletException {
        this.controller = ControllerFactory.getController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        controller.deleteDress(id);

        resp.sendRedirect(getServletContext().getContextPath()  + "/list");
    }

}
