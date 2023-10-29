package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccesRequest(request, response);
    }

    

    public void proccesRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();

            if ("clear".equals(action)) {
                session.invalidate();
                sendError(response, "Данные успешно удалены");
                return;
            }

            if (request.getParameter("R") == null || request.getParameter("X") == null || request.getParameter("Y") == null) {
                sendError(response, "Please set the data values in correct form.");
                return;
            } 
            if (request.getParameter("R").isEmpty() || request.getParameter("X").isEmpty() || request.getParameter("Y").isEmpty()) {
                sendError(response, "Please set the data values in correct form.");
                return;
            } 
            if (Integer.parseInt(request.getParameter("R")) < 2 || Integer.parseInt(request.getParameter("R")) > 5) {
                sendError(response, "Please set the data values in correct form.");
                return;
            }

            Float.parseFloat(request.getParameter("X"));
            Float.parseFloat(request.getParameter("Y"));
            Integer.parseInt(request.getParameter("R"));
            
                
            response.sendRedirect("./checkArea?"+request.getQueryString());
        } catch (Exception e) {
            sendError(response, e.toString());
        }
    }

    private void sendError(HttpServletResponse response, String errorMessage) throws IOException {
        Gson json = new Gson();
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("error", errorMessage);

        response.setContentType("application/json");
        response.getWriter().write(json.toJson(jsonResponse));
    }

}
