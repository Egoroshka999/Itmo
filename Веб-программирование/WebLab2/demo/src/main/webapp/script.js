window.addEventListener("load", () => {
    let R = document.getElementById("R");
    let Y = document.getElementById("Y");
    let img = document.getElementById("svg");
    let table = document.getElementById("result");
    let clear = document.getElementById("clear");
    let r = document.getElementById("R");

    for (let item of table.rows) {
            let x = parseFloat(item.children[0].innerText.trim());
            let y = parseFloat(item.children[1].innerText.trim());
            let r = parseFloat(item.children[2].innerText.trim());
            let result = item.children[3].innerText.trim()=="true";
            if (isNaN(x) || isNaN(y) || isNaN(r))
                continue;
            drawPoint(x, y, r, result);
    };
    

    img.addEventListener("click", function(event) {
        let svg = document.getElementById("svg");
        let point = svg.createSVGPoint();
        

        point.x = event.clientX;
        point.y = event.clientY;
        
        let ctm = svg.getScreenCTM();
        if (ctm) {
            let invertedCTM = ctm.inverse();
            let svgPoint = point.matrixTransform(invertedCTM);
            let planeCoords = transformSvgToPlane(svgPoint.x, svgPoint.y, r.value);
            sendCoordinatesToServer(planeCoords.x.toFixed(2), planeCoords.y.toFixed(2), r.value);
        }
    });

    function transformSvgToPlane(svgX, svgY, r) {
        const svgSize = 200;
        const centerX = svgSize / 2;
        const centerY = svgSize / 2;
    
        let planeX = (svgX - centerX) / (svgSize / (4 * r));
        let planeY = (centerY - svgY) / (svgSize / (4 * r));
    
        return { x: planeX, y: planeY };
    }

    async function sendCoordinatesToServer(x, y, r) {
    
        const data = await api(x, y, r);
        
        if (!data.error) {
            drawPoint(x, y, r, data.result);
            innerText(x, y, r, data.result);
        }
    }

    async function api(x, y, r) {
        let form = new FormData();
        form.append("X", x);
        form.append("Y", y);
        form.append("R", r);
        form.append("action", "checkPoint")

        let url = "controller?" + new URLSearchParams(form).toString();

        let response = await fetch(url, { method: "GET" });

        if (!response.ok) {
            throw new Error("Network response was not ok");
        }

        let data = await response.json();

        if (data.error)
            alert(data.error);

        return data;
    };

    clear.addEventListener("click", clear1);

    function clear1() {
        while (table.rows.length > 1) {
            table.deleteRow(table.rows.length - 1);
        };
    
        const circles = svg.querySelectorAll('circle');
        circles.forEach(circle => {
            if (circle.parentNode === svg) {
                svg.removeChild(circle);
            }
        });
    
        apiClear();
        
    
    };

    async function apiClear() {
        let form = new FormData();
        form.append("action", "clear")
        let url = "controller?" + new URLSearchParams(form).toString();

        let response = await fetch(url, { method: "GET" });
        let data = await response.json();
    };

    function drawPoint(x, y, r, result) {
        let svg = document.getElementById('svg');
        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", (x / r) * 50 + 100);
        circle.setAttribute("cy", (-y / r) * 50 + 100);
        circle.setAttribute("r", 2);
        circle.setAttribute("fill", result ? "green" : "red");
        svg.appendChild(circle);

    }

    function innerText(x, y, r,result) {
        let newRow = table.insertRow();
        newRow.insertCell().innerText = x;
        newRow.insertCell().innerText = y;
        newRow.insertCell().innerText = r;
        newRow.insertCell().innerText = result;
    }

    Y.addEventListener("input", () => {
        if (!/^-?\d*\.?\d*$/g.test(Y.value))
            Y.value = (Y.value.match(/(^-|\d)/g) || []).join("");
    });

    R.addEventListener("input", () => {
        if (!/^\d*$/g.test(R.value))
            R.value = (R.value.match(/(^\d*)/g) || []).join("");
    });
})

