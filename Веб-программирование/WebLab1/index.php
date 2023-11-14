<html>

<head>
    <title>Lab 1</title>
    <link rel="stylesheet" href="style.css">
    <script src="script.js" defer></script>
</head>

<body>
    <form id="form">
        <div class="table_background hide_background">
            <div class="header">Деревягин Егор Андреевич, P3215, вариант 368107</div>
            <div class="row">
                <div class="input">
                    Введите координату X амогуса
                    <input class="inputX" type="text" placeholder="-5..3" id="X" name="X" pattern="^-[0-5]|[0-3]" required>
                </div>

                <div class="input">
                    Выберите координату Y амогуса
                    <div class="checkBox">
                        <div>
                            <input type="checkbox" id="-5Y" name="Y" value="-5Y">
                            <label for="-5Y">-5</label>
                        </div>
                        <div>
                            <input type="checkbox" id="-4Y" name="Y" value="-4Y">
                            <label for="-4Y">-4</label>
                        </div>
                        <div>
                            <input type="checkbox" id="-3Y" name="Y" value="-3Y">
                            <label for="-3Y">-3</label>
                        </div>
                        <div>
                            <input type="checkbox" id="-2Y" name="Y" value="-2Y">
                            <label for="-2Y">-2</label>
                        </div>
                        <div>
                            <input type="checkbox" id="1Y" name="Y" value="-1Y">
                            <label for="-1Y">-1</label>
                        </div>
                        <div>
                            <input type="checkbox" id="0Y" name="Y" value="0Y">
                            <label for="0Y">0</label>
                        </div>
                        <div>
                            <input type="checkbox" id="1Y" name="Y" value="1Y">
                            <label for="1Y">1</label>
                        </div>
                        <div>
                            <input type="checkbox" id="2Y" name="Y" value="2Y">
                            <label for="2Y">2</label>
                        </div>
                        <div>
                            <input type="checkbox" id="3Y" name="Y" value="3Y">
                            <label for="3Y">3</label>
                        </div>
                    </div>
                </div>
                <div>
                    <svg id="svg" width="200" height="200" xmlns="http://www.w3.org/2000/svg">
                        <path d="M 100 100 L 125 100 A 25 25 0 0 0 100 75 L 100 100 L 100 50 L 50 50 L 50 100 L 100 100 L 100 150 L 150 100" fill="#3399ff" />
                        <path d="M 0 100 L 200 100 L 195 95 L 195 105 L 200 100 Z" fill="#000000" stroke="#000000" />
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
                    Выберите размер амогуса R
                    <select class="inputR" id="R" name="R" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

                <div>
                    <input type="submit" value="Check" class="button" />
                </div>


                <div>
                    <table id="result" cellspacing="0">
                        <tr>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Result</th>
                            <th>Time</th>
                            <th>Exec</th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </form>

    <div>
        <button type="button" onclick="clear1()" class="button">
            Clear
        </button>
    </div>
</body>

</html>