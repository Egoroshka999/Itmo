from math import inf, sqrt
from scipy.integrate import quad

from utils import *
from lab3dop.calculations import *

functions = [
    ((lambda x: 1 / sqrt(x)), [0], (0, inf)),
    ((lambda x: 1 / sqrt(1 - x ** 2)), [-1, 1], (-1, 1)),
    ((lambda x: 1/(x-1)), [1], (-inf, inf)),
    ((lambda x: 1/sqrt(2*x - x ** 2)), [0,2], (0, 2))
]

def checkDivergent(f, x, e, sign=1):
    n = round(1 / e)
    new_e = e
    last = 0
    for i in range(20):
        new_e = new_e / 2
        a = x + new_e * sign
        b = x + e * sign
        try:
            f(a)
        except:
            break
        interval = [a, b]
        interval.sort()
        t = simpsonMethod(f, interval[0], interval[1], n)
        print('check', t)
        if abs(last - t) > n:
            print(a)
            raise ValueError('Ошибка, интеграл расходится')
        last = t
    return last

def calcIntervals(func, a, b, e):
    f, breakPoints, defined = func
    min, max = defined

    if a < min or b > max:
        raise Exception('Интеграл не существует')

    points = [a, b]
    for point in breakPoints:
        if point >= a and point <= b:
            points.append(point)

    points = sorted(set(points))

    intervals = [(points[i], points[i + 1]) for i in range(len(points) - 1)]

    fin = []

    for a, b in intervals:
        n_a = a
        n_b = b
        t = 0
        if a in breakPoints:
            n_a += e
            t += checkDivergent(f, a, e, 1)
        if b in breakPoints:
            n_b -= e
            t += checkDivergent(f, b, e, -1)
        fin.append([n_a, n_b, t])

    return fin

def solveIntegral():
    print("Выберите интеграл, который вы хотите вычислить")
    chooseIntegralDop()
    number = inputInt("Введите номер: ", "Номер должен быть представлен числом")
    if number == 1:
        f = functions[number-1]
    elif number == 2:
        f = functions[number-1]
    elif number == 3:
        f = functions[number-1]
    elif number == 4:
        f = functions[number-1]
    else:
        print("Вы ввели не существующюю функцию, попробуйте ещё раз")
        exit(1)

    a = inputFloat("Введите нижнюю границу: ", "Граница должна быть представлена числом")
    b = inputFloat("Введите верхнюю границу: ", "Граница должна быть представлена числом")


    if (a > b):
        print("Нижняя граница больше верхней, значение интеграла может получиться c обратным знаком")
        print("1 - Да")
        print("2 - Нет")
        confirm = inputInt("Продолжить? ", "Значение должно быть числом")
        if confirm == 1:
            pass
        elif confirm == 2:
            exit(0)
        else:
            print("Вы ввели не существующий номер ответа")
            exit(1)

    n = 4
    e = inputFloat("Введите точность: ", "Значение должно быть представленно числом")
    if e <= 0:
        print("Точность должна быть больше 0")
        exit(1)

    print("Выберите метод:")
    chooseMethod()
    m = inputInt("Введите номер: ", "Номер должен быть представлен числом")
    calcIntervals(f, a, b, e)

    if m == 1:
        values, interval = rungeRule(f[0], a, b, n, e, rectangleMethod, 1, 1)
        print("Левые прямоугольники:")
        printSolve(values, interval)
        values, interval = rungeRule(f[0], a, b, n, e, rectangleMethod, 1, 2)
        print("Правые прямоугольники:")
        printSolve(values, interval)
        values, interval = rungeRule(f[0], a, b, n, e, rectangleMethod, 2, 3)
        print("Средние прямоугольники:")
        printSolve(values, interval)
    elif m == 2:
        values, interval = rungeRule(f[0],a,b,n,e,trapezoidMethod,2)
        printSolve(values, interval)
    elif m == 3:
        values, interval = rungeRule(f[0],a,b,n,e,simpsonMethod,4)
        printSolve(values, interval)
    else:
        print("Вы ввели не существующий номер метода, попробуйте ещё раз")

    result, error = quad(f[0], a, b)
    print("Интегрирование с помощью библиотеки:", result)
    print("Оценка ошибки:", error)

if __name__ == '__main__':
    try:
        solveIntegral()
    except Exception as e:
        print(e)

