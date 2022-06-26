package ru.gretchen.dressshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Getter
public class HttpRequestResponseWrapper {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
}
