package de.kirschners.example.jetty.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloHandler extends AbstractHandler {
    final String _greeting;
    final String _body;

    public HelloHandler() {
        _greeting = "Hello World";
        _body = null;
    }

    public HelloHandler(final String greeting) {
        _greeting = greeting;
        _body = null;
    }

    public HelloHandler(final String greeting, final String body) {
        _greeting = greeting;
        _body = body;
    }

    @Override
    public void handle(final String target,
                       final Request baseRequest,
                       final HttpServletRequest request,
                       final HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        response.getWriter().println("<h1>" + _greeting + "</h1>");
        response.getWriter()
                .println("last called on: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(System.currentTimeMillis()));
        if (_body != null) {
            response.getWriter().println(_body);
        }
    }

}
