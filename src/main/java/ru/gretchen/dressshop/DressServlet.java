package ru.gretchen.dressshop;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DressServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                createNewDress(request, response);
                break;
            case "/get":
                getDress(request, response);
                break;
            case "/get-all":
                getAllDresses(request, response);
                break;
            case "/update":
                updateDress(request, response);
                break;
            case "/delete":
                deleteDress(request, response);
                break;
        }
    }

    private void createNewDress(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void getDress(HttpServletRequest request, HttpServletResponse response) {

    }

    private void getAllDresses(HttpServletRequest request, HttpServletResponse response) {

    }

    private void updateDress(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteDress(HttpServletRequest request, HttpServletResponse response) {

    }
}
