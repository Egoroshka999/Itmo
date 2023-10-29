package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkArea")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private boolean isInside(float x, float y, float r) {
        if (x <= r && y <= r) {
            if (x >= 0 && y >= 0) {
                float a = (0 - x) * (0 - 0) - (r / 2 - 0) * (0 - y);
                float b = (r / 2 - x) * (r - 0) - (0 - r / 2) * (0 - y);
                float c = (0 - x) * (0 - r) - (0 - 0) * (r - y);
                if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
                    return true;
                }
            } else if (x < 0 && y > 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow((-r / 2), 2)) {
                return true;
            } else if (x < 0 && y < 0 && x >= -r / 2 && y >= -r) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Float x = Float.parseFloat(request.getParameter("X"));
            Float y = Float.parseFloat(request.getParameter("Y"));
            Integer r = Integer.parseInt(request.getParameter("R"));
            HttpSession session = request.getSession();

            List<String[]> results = (List<String[]>) session.getAttribute("results");
            if (results == null) {
                results = new ArrayList<>();
                session.setAttribute("results", results);
            }

            Boolean result = isInside(x, y, r);

            results.add(new String[] { x.toString(), y.toString(), r.toString(), result.toString() });
            //String.format("%.2f", x).toString(), String.format("%.2f", y).toString()

            String action = request.getParameter("action");
            if ("submitForm".equals(action)) {
                request.setAttribute("X", x);
                request.setAttribute("Y", y);
                request.setAttribute("R", r);
                request.setAttribute("result", result.toString());
                RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
                dispatcher.forward(request, response);
            } else if ("checkPoint".equals(action)) {
                Gson json = new Gson();
                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("x", x);
                jsonResponse.put("y", y);
                jsonResponse.put("r", r);
                jsonResponse.put("result", result);

                response.setContentType("application/json");
                response.getWriter().write(json.toJson(jsonResponse));
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }
    }
}
