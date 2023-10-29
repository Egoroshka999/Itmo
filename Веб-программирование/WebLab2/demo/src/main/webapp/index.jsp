<!-- <html>
    <head>
        <title> Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="./api" method="POST">
            <label>Name of you</label>
            <>
            <input type="submit" value="Click here">
        </form>
    </body>
</html> -->
<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <html>

        <head>
            <title>Lab 1</title>
            <link rel="stylesheet" href="style.css">
            <script src="script.js" defer></script>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>

        <body>
            <form id="form" action="controller" method="get">
                <input type="hidden" name="action" value="submitForm">
                <div class="table_background hide_background">
                    <div class="header">Деревягин Егор Андреевич, P3215, вариант 368108</div>
                    <div class="row">
                        <div class="input">
                            Выберите координату X амогуса
                            <div class="checkBox">
                                <div>
                                    <input type="radio" id="-3X" name="X" value="-3">
                                    <label for="-3X">-3</label>
                                </div>
                                <div>
                                    <input type="radio" id="-2X" name="X" value="-2">
                                    <label for="-2X">-2</label>
                                </div>
                                <div>
                                    <input type="radio" id="-1X" name="X" value="-1">
                                    <label for="-1X">-1</label>
                                </div>
                                <div>
                                    <input type="radio" id="0X" name="X" value="0">
                                    <label for="0X">0</label>
                                </div>
                                <div>
                                    <input type="radio" id="1X" name="X" value="1">
                                    <label for="1X">1</label>
                                </div>
                                <div>
                                    <input type="radio" id="2X" name="X" value="2">
                                    <label for="2X">2</label>
                                </div>
                                <div>
                                    <input type="radio" id="3X" name="X" value="3">
                                    <label for="3X">3</label>
                                </div>
                                <div>
                                    <input type="radio" id="4X" name="X" value="4">
                                    <label for="4X">4</label>
                                </div>
                                <div>
                                    <input type="radio" id="5X" name="X" value="5">
                                    <label for="5X">5</label>
                                </div>
                            </div>
                        </div>

                        <div class="input">
                            Введите координату Y амогуса
                            <input class="inputText" type="text" placeholder="-5..3" id="Y" name="Y"
                                pattern="^-[0-5]\.?\d*|[0-3]\.?\d*" required>
                        </div>

                        <div>
                            <svg class="graph" id="svg" xmlns="http://www.w3.org/2000/svg">
                                <path
                                    d="M 100 100 L 75 100 A 25 25 0 0 1 100 75 L 100 100 L 125 100 L 100 50 L 100 100 L 100 150 L 75 150 L 75 100 Z"
                                    fill="#3399ff" />
                                <path d="M 0 100 L 200 100 L 195 95 L 195 105 L 200 100 Z" fill="#000000"
                                    stroke="#000000" />
                                <path d="M 100 200 L 100 0 L 95 5 L 105 5 L 100 0 Z" fill="#000000" stroke="#000000" />
                                <g fill="#000000" stroke="#000000">
                                    <circle cx="100" cy="150" r="1.5" />
                                    <circle cx="100" cy="125" r="1.5" />
                                    <circle cx="100" cy="75" r="1.5" />
                                    <circle cx="100" cy="50" r="1.5" />
                                    <circle cx="50" cy="100" r="1.5" />
                                    <circle cx="75" cy="100" r="1.5" />
                                    <circle cx="125" cy="100" r="1.5" />
                                    <circle cx="150" cy="100" r="1.5" />
                                </g>
                                <g font-size="6px" dominant-baseline="middle" text-anchor="middle">
                                    <text x="50" y="95">-R</text>
                                    <text x="75" y="95">-R/2</text>
                                    <text x="125" y="95">R/2</text>
                                    <text x="150" y="95">R</text>
                                </g>
                                <g font-size="6px" dominant-baseline="middle" text-anchor="left">
                                    <text x="102" y="150">-R</text>
                                    <text x="102" y="125">-R/2</text>
                                    <text x="102" y="75">R/2</text>
                                    <text x="102" y="50">R</text>
                                </g>
                                <g font-size="8px">
                                    <text x="197" y="90" dominant-baseline="middle" text-anchor="middle">x</text>
                                    <text x="110" y="4" dominant-baseline="middle" text-anchor="middle">y</text>
                                </g>
                            </svg>
                        </div>
                    </div>

                    <div class="row2">
                        <div class="input">
                            Введите размер амогуса R
                            <input class="inputText" type="text" placeholder="2..5" id="R" name="R" pattern="[2-5]"
                                required>
                        </div>

                        <div>
                            <input type="submit" value="Check" class="button" />
                        </div>

                        <div>
                            <button id="clear" type="button" class="button">
                                Clear
                            </button>
                        </div>
                    </div>

                    <div>
                        <div class="table_container">
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
                                    <% List<String[]> results = (List<String[]>)
                                            request.getSession().getAttribute("results");
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
                    </div>
                </div>
            </form>
        </body>

        </html>