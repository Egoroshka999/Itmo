from functools import reduce
from math import factorial
import numpy as np
from matplotlib import pyplot as plt

def lagrange_polynomial(xs, ys, n):
    return lambda x: sum([
        ys[i] * reduce(
            lambda a, b: a * b,
                        [(x - xs[j]) / (xs[i] - xs[j])
            for j in range(n) if i != j])
        for i in range(n)])

def divided_differences(x, y):
    n = len(y)
    coef = np.copy(y).astype(float)
    for j in range(1, n):
        for i in range(n-1, j-1, -1):
            coef[i] = (coef[i] - coef[i-1]) / (x[i] - x[i-j])
    return coef

def newton_divided_difference_polynomial(xs, ys, n):
    coef = divided_differences(xs, ys)
    return lambda x: ys[0] + sum([
        coef[k] * reduce(lambda a, b: a * b, [x - xs[j] for j in range(k)]) for k in range(1, n)
    ])


def finite_differences(y):
    n = len(y)
    delta_y = np.zeros((n, n))
    delta_y[:,0] = y
    for j in range(1, n):
        for i in range(n-j):
            delta_y[i,j] = delta_y[i+1,j-1] - delta_y[i,j-1]
    return delta_y

def print_finite_differences_table(delta_y):
    n = delta_y.shape[0]
    print("Таблица конечных разностей:")
    for i in range(n):
        row = [f"{delta_y[i, j]:.4f}" if i + j < n else "" for j in range(n)]
        print("\t".join(row))

def newton_finite_difference_polynomial(xs, ys, n):
    delta_y = finite_differences(ys)
    h = xs[1] - xs[0]

    def polynomial(x):
        m = []
        x_a = (xs[0] + xs[-1]) / 2

        if x < x_a: # Интерполирование вперёд
            t = (x - xs[0]) / h
            N = ys[0]
            m.append(ys[0])
            p = 1
            for i in range(1, n):
                p *= (t - i + 1)
                N += (p / factorial(i)) * delta_y[0, i]
                m.append(delta_y[0, i])
        else: # Интерполирование назад
            t = (x - xs[-1]) / h
            N = ys[-1]
            m.append(ys[-1])
            p = 1
            for i in range(1, n):
                p *= (t + i - 1)
                N += (p / factorial(i)) * delta_y[len(xs) - i - 1, i]
                m.append(delta_y[len(xs) - i - 1, i])

        return N

    return polynomial

def generate_dts1(n):
    dts1 = [0]
    for i in range(1, n):
        dts1.append(-i)
        dts1.append(i)
    return dts1

def stirling_polynomial(xs, ys, n):
    n = len(xs) - 1
    alpha_ind = n // 2
    fin_difs = []
    fin_difs.append(ys[:])

    for k in range(1, n + 1):
        last = fin_difs[-1][:]
        fin_difs.append(
            [last[i + 1] - last[i] for i in range(n - k + 1)])

    h = xs[1] - xs[0]
    dts1 = generate_dts1(n)

    def f1(x):
        result = ys[alpha_ind]
        for k in range(1, n + 1):
            term = 1
            for j in range(k):
                term *= (x - xs[alpha_ind]) / h + dts1[j]
            result += term * fin_difs[k][len(fin_difs[k]) // 2] / factorial(k)
        return result

    def f2(x):
        result = ys[alpha_ind]
        for k in range(1, n + 1):
            term = 1
            for j in range(k):
                term *= (x - xs[alpha_ind]) / h - dts1[j]
            result += term * fin_difs[k][len(fin_difs[k]) // 2 - (1 - len(fin_difs[k]) % 2)] / factorial(k)
        return result

    return lambda x: (f1(x) + f2(x)) / 2

def create_factorial(n):
    result = []
    for i in range(n+1):
        fact = 1
        for j in range(1, i+1):
            fact *= j
        result.append(fact)
    return result

def bessel_polynomial(x_arr, y_arr, x):
    if len(y_arr) % 2 == 1:
        raise ValueError("Length of y_arr must be even.")
    h = x_arr[1] - x_arr[0]
    fin_diff = finite_differences(y_arr)
    fact = create_factorial(len(y_arr))
    mid = (len(y_arr) - 2) // 2

    def polynomial(x):
        t = (x - x_arr[mid]) / h
        result = 0

        for n in range(0, mid + 1):
            mul = 1
            for j in range(1, n + 1):
                mul *= (t - j) * (t + j - 1)
            result += (1 / fact[2 * n]) * mul * (fin_diff[mid - n][2 * n] + fin_diff[mid - n + 1][2 * n]) / 2
            result += (1 / fact[2 * n + 1]) * (t - 0.5) * mul * fin_diff[mid - n][2 * n + 1]

        return result

    return polynomial


def draw_plot(a, b, func, name, dx=0.001):
    xs, ys = [], []
    a -= dx
    b += dx
    x = a
    while x <= b:
        xs.append(x)
        ys.append(func(x))
        x += dx
    plt.plot(xs, ys, 'g', label=name)


def solve(xs, ys, x, n):
    delta_y = finite_differences(ys)
    print_finite_differences_table(delta_y)

    print('\n' + '-' * 60)

    methods = [("Многочлен Лагранжа", lagrange_polynomial),
               ("Многочлен Ньютона с разделенными разностями", newton_divided_difference_polynomial),
               ("Многочлен Ньютона с конечными разностями", newton_finite_difference_polynomial),
               ("Многочлен Стирлинга", stirling_polynomial),
               ("Многочлен Бесселя", bessel_polynomial)]

    for name, method in methods:
        finite_difference = True
        last = xs[1] - xs[0]
        for i in range(1, n):
            new = abs(xs[i] - xs[i - 1])
            if abs(new - last) > 0.0001:
                finite_difference = False
            last = new

        if (method is newton_finite_difference_polynomial) and not finite_difference:
            continue

        if (method is newton_divided_difference_polynomial) and finite_difference:
            continue

        if (method is stirling_polynomial) and len(xs) % 2 == 0:
            continue

        if method is bessel_polynomial and (len(xs) % 2 == 1 or not finite_difference):
            continue

        h = xs[1] - xs[0]
        alpha_ind = n // 2
        t = (x - xs[alpha_ind]) / h
        print("t: ", t)

        print(name)
        P = method(xs, ys, n)
        print(f'P({x}) = {P(x)}')
        print('-' * 60)

        plt.title(name)
        draw_plot(xs[0], xs[-1], P, name)
        plt.xlabel("X")
        plt.ylabel("Y")
        plt.scatter(x, P(x), c='r')
        for i in range(len(xs)):
            plt.scatter(xs[i], ys[i], c='b')

        plt.show()