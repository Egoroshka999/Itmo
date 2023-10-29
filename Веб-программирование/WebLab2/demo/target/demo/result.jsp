<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Результаты</title>
            <link rel="stylesheet" href="style.css">
        </head>

        <body>
            <h2>Результаты проверки:</h2>
            <div class="table_container table_background">
                <table id="result" cellspacing="0">
                    <thead>
                        <tr>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Результат</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<String[]> results = (List<String[]>) request.getSession().getAttribute("results");
                                if (results != null) {
                                for (String[] result : results) {
                                %>
                                <tr>
                                    <td>
                                        <%= result[0] %>
                                    </td>
                                    <td>
                                        <%= result[1] %>
                                    </td>
                                    <td>
                                        <%= result[2] %>
                                    </td>
                                    <td>
                                        <%= result[3] %>
                                    </td>
                                </tr>
                                <% } } %>
                    </tbody>
                </table>
            </div>
            <p>
                <a href="index.jsp">Вернуться к форме</a>
            </p>

        </body>

        </html>