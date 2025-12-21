package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        // Status must be 200
        response.setStatus(HttpServletResponse.SC_OK);

        // Content type must be text/plain
        response.setContentType("text/plain");

        // Exact response message expected by tests
        response.getWriter()
                .write("API Rate Limiter & Quota Manager is running");
    }
}
