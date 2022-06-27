package ru.gretchen.dressshop.servlet;

import lombok.SneakyThrows;
import ru.gretchen.dressshop.controller.CustomerController;
import ru.gretchen.dressshop.controller.CustomerControllerFactory;
import ru.gretchen.dressshop.model.CustomerEntity;
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

public class CustomerServlet extends HttpServlet {

    private CustomerController controller;
    private Map<String, Map<String, BiConsumer<CustomerServlet, HttpRequestResponseWrapper>>> handlingMap;

    @Override
    public void init() throws ServletException {
        this.controller = CustomerControllerFactory.getController();
        handlingMap = new HashMap<>();

        Map<String, BiConsumer<CustomerServlet, HttpRequestResponseWrapper>> getMap = new HashMap<>();
        Map<String, BiConsumer<CustomerServlet, HttpRequestResponseWrapper>> postMap = new HashMap<>();

        getMap.put("/list", CustomerServlet::getList);
        getMap.put("/edit", CustomerServlet::getCustomerForm);
        getMap.put("/add", CustomerServlet::getCustomerForm);
        getMap.put("/delete", CustomerServlet::deleteCustomer);
        postMap.put("/edit", CustomerServlet::updateCustomer);
        postMap.put("/add", CustomerServlet::addCustomer);

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
        List<CustomerEntity> customers = controller.getAllCustomers();
        wrapper.getRequest().setAttribute("customers", customers);

        String path = "/view/customer-list.jsp";

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(wrapper.getRequest(), wrapper.getResponse());
    }

    @SneakyThrows
    private void getCustomerForm(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        HttpServletResponse resp = wrapper.getResponse();

        String stringId = req.getParameter("id");

        if (Objects.nonNull(stringId)) {
            Long id = Long.valueOf(req.getParameter("id"));
            CustomerEntity customer = controller.getCustomer(id);

            req.setAttribute("customer", customer);
        }

        String path = "/view/customer-form.jsp";
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @SneakyThrows
    private void updateCustomer(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();

        Long id = Long.valueOf(req.getParameter("id"));

        CustomerEntity customer = customerFromRequest(req);
        controller.updateCustomer(id, customer);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void addCustomer(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        CustomerEntity customer = customerFromRequest(req);
        controller.createCustomer(customer);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    @SneakyThrows
    private void deleteCustomer(HttpRequestResponseWrapper wrapper) {
        HttpServletRequest req = wrapper.getRequest();
        Long id = Long.valueOf(req.getParameter("id"));

        controller.deleteCustomer(id);

        wrapper.getResponse().sendRedirect(getServletContext().getContextPath()  + req.getServletPath() + "/list");
    }

    private CustomerEntity customerFromRequest(HttpServletRequest req) {
        String firstName = String.valueOf(req.getParameter("firstName"));
        String lastName = String.valueOf(req.getParameter("lastName"));
        String phoneNumber = String.valueOf(req.getParameter("phoneNumber"));

        return new CustomerEntity(firstName, lastName, phoneNumber);
    }
}
