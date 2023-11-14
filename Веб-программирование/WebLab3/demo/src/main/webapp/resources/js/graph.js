let calculator;

window.onload = function () {
    let rValue = parseFloat(document.getElementById('form:slider').value);
    let Y = document.getElementById('form:yInput');
    document.getElementById("form:sliderDisplay").value=rValue;
    let elt = document.getElementById('calculator');
    calculator = Desmos.GraphingCalculator(elt, {
        keypad: false,
        expressions: false,
        settingsMenu: false,
        xAxisLabel: 'x',
        yAxisLabel: 'y',
        xAxisStep: 1,
        yAxisStep: 1,
        xAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
        yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE
    });


    calculator.setMathBounds({
        left: -5,
        right: 5,
        bottom: -5,
        top: 5
    });

    setGraphExpressions(2);

    document.getElementById('calculator').addEventListener('click', function (evt) {
        handleDesmosClick(evt);
    });


    Y.addEventListener("input", () => {
        if (!/^-?\d*\.?\d*$/g.test(Y.value))
            Y.value = (Y.value.match(/(^-|\d*\.?\d)/g) || []).join("");
    });

}

function setGraphExpressions(value) {
    calculator.setExpression({ id: 'circle', latex: 'x^{2} + y^{2} \\le \\left(\\frac{' + value + '}{2}\\right)^{2} \\{ -\\frac{' + value + '}{2} \\le x \\le 0 \\} \\{ -\\frac{' + value + '}{2} \\le y \\le 0 \\}', color: Desmos.Colors.BLUE });
    calculator.setExpression({ id: 'line1', latex: 'y = \\frac{' + value + '}{2} \\{ -' + value + ' \\le x \\le 0 \\}', color: Desmos.Colors.BLUE });
    calculator.setExpression({ id: 'square', latex: '-' + value + ' \\le x \\le 0 \\{ 0 \\le y \\le \\frac{' + value + '}{2} \\}', color: Desmos.Colors.BLUE });
    calculator.setExpression({ id: 'triangle', latex: '\\frac{1}{2}x - \\frac{' + value + '}{2} \\le y \\le 0 \\{ 0 \\le x \\le ' + value + ' \\}', color: Desmos.Colors.BLUE });
}

function updateR() {
    console.log("Function updateR called!");
    let rValue = parseFloat(document.getElementById('form:slider').value);
    document.getElementById("form:sliderDisplay").value=rValue;
    console.log("Updated R value:", rValue);
    calculator.removeExpressions(calculator.getExpressions());
    setGraphExpressions(rValue);
}

function drawPointOnDesmos() {
    let lastCheckedPointX = parseFloat(document.getElementById("form:hiddenX").value);
    let lastCheckedPointY = parseFloat(document.getElementById("form:yInput").value);
    let isInside = document.getElementById("form:insideValue").value;
    
   if (lastCheckedPointX < -2 || lastCheckedPointX > 1.5 || lastCheckedPointY < -5 || lastCheckedPointY > 3){
    alert("Validation Failed, check coordinates!");
   }
    else if (lastCheckedPointX && lastCheckedPointY) {
        console.log("abobus");
        const color = isInside === 'true' ? 'green' : 'red';
        const uniquePointId = 'point' + new Date().getTime();

        calculator.setExpression({
            id: uniquePointId,
            latex: '(' + lastCheckedPointX + ',' + lastCheckedPointY + ')',
            color: color
        });
    }
}

function checkPoint() {
    executeCheckPoint();
}

function handleDesmosClick(evt) {
    // Получение размеров области координатной плоскости
    let bounds = calculator.graphpaperBounds;
    let elt = document.getElementById('calculator');
    let width = elt.offsetWidth;
    let height = elt.offsetHeight;


    // Получение текущих границ координатной плоскости
    let mathBounds = bounds.mathCoordinates;

    // Преобразование экранных координат в координаты на координатной плоскости
    let mathX = (evt.offsetX / width) * (mathBounds.right - mathBounds.left) + mathBounds.left;
    let mathY = mathBounds.top - (evt.offsetY / height) * (mathBounds.top - mathBounds.bottom);

    
    document.getElementById('form:hiddenX').value = mathX;
    document.getElementById('form:yInput').value = mathY;
    const rValue = parseFloat(document.getElementById('form:slider').value);
    console.log(mathX, mathY, rValue);
    checkPoint();
}

