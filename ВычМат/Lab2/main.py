from utils import *
from calculations import *
import matplotlib.pyplot as plt
import numpy as np
from sympy import *


def solveEqualisation():
    chooseEqualisation()
    equalNumber = inputInt("Выберите уравнение: ", "Номер должен быть представлен числом")

    fig, ax = plt.subplots()
    ax.set_title('График функции')
    ax.set_xlabel('x')
    ax.set_ylabel('y')
    plt.grid(True)

    a = inputFloat("Введите левую границу: ", "Граница должна быть представлена числом")
    b = inputFloat("Введите правую границу: ", "Граница должна быть представлена числом")
    h = inputFloat("Введите шаг: ", "Шаг должен быть числом")
    x = np.linspace(a, b, 100)

    if (equalNumber == 1):
        y = x**3 + 2.28*x**2 - 1.934*x - 3.907
        solves = searchSolves(a, b, h, equation1)
        equation = equation1
        diffEquation = diffEquation1
    elif(equalNumber == 2):
        y = 5*np.sin(x) + 0.2
        solves = searchSolves(a, b, h, equation2)
        equation = equation2
        diffEquation = diffEquation2
    elif (equalNumber == 3):
        y = 0.3*x**2 + np.cos(x**2) - 7
        solves = searchSolves(a, b, h, equation3)
        equation = equation3
        diffEquation = diffEquation3
    else:
        print("Вы ввели не существующий номер уравнения, попробуйте ещё раз")

    plt.plot(x, y, 'b', alpha=0.8)
    plt.show()
    print("Интересные промежутки: ", *solves)
    if(len(solves) == 0):
        print("Корней уровнения не найдено")
    a = inputFloat("Введите левую границу: ", "Граница должна быть представлена числом")
    b = inputFloat("Введите правую границу: ", "Граница должна быть представлена числом")
    e = inputFloat("Введите точность: ", "Значение должно быть представленно числом")

    chooseMethod()
    methodNumber = inputInt("Выберите метод решения: ", "Номер должен быть представлен числом")
    if (methodNumber == 1):
        x, f, n = methogHalfDiv(a, b, e, equation)
        printSolve(x, f, n)
    elif (methodNumber == 2):
        x, f, n = methodCut(a, b, e, equation)
        printSolve(x, f, n)
    elif (methodNumber == 3):
        try:
            x, f, n = methodSimpIter(a, b, e, equation, diffEquation)
            printSolve(x, f, n)
        except TypeError:
            print("Не выполняется достаточное условие сходимости")
    else:
        print("Вы ввели не существующий номер метода, попробуйте ещё раз")


def solveSystem():
    x = np.linspace(-2, 2, 400)
    y = np.linspace(-2, 2, 400)
    X, Y = np.meshgrid(x, y)

    chooseSystem()
    systemNum = inputInt("Введите номер системы: ", "Номер системы должен быть числом")

    if (systemNum == 1):
        Z1 = np.tan(X * Y + 0.2) - X * X
        Z2 = X ** 2 + 2 * Y ** 2 - 1

        f = f1
        g = g1
        dfdx = dfdx1
        dfdy = dfdy1
        dgdx = dgdx1
        dgdy = dgdy1

    elif (systemNum == 2):
        Z1 = np.sin(X + Y) - 1.2 * X - 0.2
        Z2 = X ** 2 + 2 * Y ** 2 - 2

        f = f2
        g = g2
        dfdx = dfdx2
        dfdy = dfdy2
        dgdx = dgdx2
        dgdy = dgdy2

    plt.figure()

    contours1 = plt.contour(X, Y, Z1, levels=[0], colors='blue')
    contours2 = plt.contour(X, Y, Z2, levels=[0], colors='red')

    plt.xlabel('x')
    plt.ylabel('y')
    plt.title('График системы уравнений')
    plt.grid(True)
    plt.show()

    x0 = inputFloat("Введите x: ", "Координата должна быть представлена числом")
    y0 = inputFloat("Введите y: ", "Координата должна быть представлена числом")
    e = inputFloat("Введите точность: ", "Значение должно быть представленно числом")
    x, y, n = newtonMethod(e, x0, y0, f, g, dfdx, dfdy, dgdx, dgdy)
    printSystemInfo(x, y, n)

    print("Вектор невязок: ")
    if (systemNum == 1):
        print(f'{abs(tan(x * y + 0.2) - x ** 2):.15f}', end=' ')
        print(f'{abs(x ** 2 + 2 * y ** 2 - 1):.15f}')
    elif (systemNum == 2):
        print(f'{abs(sin(x + y) - 1.2 * x - 0.2):.15f}', end=' ')
        print(f'{abs(x ** 2 + 2 * y ** 2 - 2):.15f}')


if __name__ == '__main__':
    print("Что вы хотите решить?")
    print("1 - Нелинейные уравнения")
    print("2 - Системы нелинейных уравнений")
    n = inputInt("Введите номер: ", "Номер должен быть представлен числом")
    if (n == 1):
        solveEqualisation()
    elif (n == 2):
        solveSystem()
    else:
        print("Вы ввели не существующюю опцию, попробуйте ещё раз")
