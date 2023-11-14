const clear1 = (e) => {
    let result = document.getElementById("result");
    let svg = document.getElementById('svg');
    const len = result.children.length;
    for (let i = 1; i < len; i += 1) {
        result.deleteRow(1);
    };

    const circles = svg.querySelectorAll('circle');
    circles.forEach(circle => {
        if (circle.parentNode === svg) {
            svg.removeChild(circle);
        }
    });
};

window.addEventListener("load", () => {
    let form = document.getElementById("form");
    let X = document.getElementById("X");
    let R = document.getElementById("R");
    let result = document.getElementById("result");
    let checkBoxes = document.getElementsByName("Y");
    let AmOgUs = new Audio("sound/AmOgUs.mp3");
    let table_background = document.getElementsByClassName("table_background")[0];

    for (checkBox of checkBoxes) {
        let current = checkBox;
        checkBox.addEventListener("change", () => {
            for (checkBox2 of checkBoxes) {
                if (checkBox2 != current)
                    checkBox2.checked = false;
            }
        })
    };

    X.addEventListener("input", () => {
        if (!/^-?\d*$/g.test(X.value))
            X.value = (X.value.match(/(^-|\d)/g) || []).join("");
    });


    form.addEventListener("submit", async event => {
        event.preventDefault();
        let Y = document.querySelector('input[name="Y"]:checked');
        try {
            let response = await api(X.value, Y.value, R.value);
            if (response.Inside) {
                AmOgUs.play();
                console.log("aboba")
                AmOgUs.currentTime = 0;
            }
            insertResult(result, response.X, response.Y, response.R, response.Inside, response.time, response.exec);
            addPoint(response.X, response.Y, response.R, response.Inside);
        } catch (error) {
            console.log(error);
            alert("Вы ввели некоретные данные");
        }
    });

    AmOgUs.addEventListener("playing", () => {
        table_background.classList.remove("hide_background");
    });

    AmOgUs.addEventListener("ended", () => {
        table_background.classList.add("hide_background");
    });
})

async function api(X, Y, R) {
    let form = new FormData;
    form.append("X", X);
    form.append("Y", Y);
    form.append("R", R);
    let data = await fetch("api.php", {
        method: "post",
        body: form
    }).then(res => res.json());
    if (data.error) throw new Error(data.error);

    return data;
}

function insertResult(table, x, y, r, inside, time, exec) {
    let row = document.createElement("tr");
    let xCell = document.createElement("td");
    let yCell = document.createElement("td");
    let rCell = document.createElement("td");
    let insideCell = document.createElement("td");
    let timeCell = document.createElement("td");
    let execCell = document.createElement("td");
    xCell.innerText = x;
    yCell.innerText = y;
    rCell.innerText = r;
    insideCell.innerText = inside ? "Yes" : "No";
    timeCell.innerText = new Date(time * 1000).toLocaleTimeString();
    execCell.innerText = exec.toFixed(8);
    row.append(xCell, yCell, rCell, insideCell, timeCell, execCell);
    table.appendChild(row);
}

function addPoint(x, y, r, inside) {
    const newCircle = document.createElementNS("http://www.w3.org/2000/svg", "circle");

    newCircle.setAttribute("cx", (x / r) * 50 + 100);
    newCircle.setAttribute("cy", (-y / r) * 50 + 100);
    newCircle.setAttribute("r", 2);
    newCircle.setAttribute("fill", inside?"yellow":"red");
    svg.appendChild(newCircle);
}