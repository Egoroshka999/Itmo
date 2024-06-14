import numpy as np
import matplotlib.pyplot as plt
import scipy
from prettytable import PrettyTable
from scipy.integrate import odeint


class ODEMethods:
    def __init__(self, func, y0, x0, xn, h, exact_solution=None, epsilon=1e-6):
        self.func = func
        self.y0 = y0
        self.x0 = x0
        self.xn = xn
        self.h = h
        self.steps = int((xn - x0) / h) + 1
        self.exact_solution = exact_solution
        self.epsilon = epsilon

    def euler(self):
        x = np.linspace(self.x0, self.xn, self.steps)
        y = np.zeros((self.steps, len(self.y0)))
        y[0] = self.y0
        for i in range(1, self.steps):
            y[i] = y[i-1] + self.h * self.func(x[i-1], y[i-1])
        return x, y

    def euler_improved(self):
        x = np.linspace(self.x0, self.xn, self.steps)
        y = np.zeros((self.steps, len(self.y0)))
        y[0] = self.y0
        for i in range(1, self.steps):
            k1 = self.func(x[i-1], y[i-1])
            k2 = self.func(x[i], y[i-1] + self.h * k1)
            y[i] = y[i-1] + (self.h * (k1 + k2)) / 2
        return x, y

    def runge_kutta_4(self):
        x = np.linspace(self.x0, self.xn, self.steps)
        y = np.zeros((self.steps, len(self.y0)))
        y[0] = self.y0
        for i in range(1, self.steps):
            k1 = self.h * self.func(x[i-1], y[i-1])
            k2 = self.h * self.func(x[i-1] + self.h / 2, y[i-1] + k1 / 2)
            k3 = self.h * self.func(x[i-1] + self.h / 2, y[i-1] + k2 / 2)
            k4 = self.h * self.func(x[i-1] + self.h, y[i-1] + k3)
            y[i] = y[i-1] + (k1 + 2 * k2 + 2 * k3 + k4) / 6
        return x, y

    def milne(self):

        h1 = self.h
        ode_methods_runge_kuta = ODEMethods(self.func, self.y0, self.x0, self.xn, h1, self.epsilon)
        x_k, y_k = ode_methods_runge_kuta.runge_kutta_4()
        ode_methods_runge_kuta = ODEMethods(self.func, self.y0, self.x0, self.xn, h1/2, self.epsilon)
        x1_k, y1_k = ode_methods_runge_kuta.runge_kutta_4()
        for i in range(int((self.xn-self.x0)/self.h) + 1):
            if (abs(y_k[i] - y1_k[i]) / (2 ** 4 - 1) > self.epsilon):
                while abs(y_k[i] - y1_k[i]) / (2 ** 4 - 1) > self.epsilon:
                    ode_methods_runge_kuta = ODEMethods(self.func, self.y0, self.x0, self.xn, h1, self.epsilon)
                    x_k, y_k = ode_methods_runge_kuta.runge_kutta_4()
                    h1 /= 2
                    ode_methods_runge_kuta = ODEMethods(self.func, self.y0, self.x0, self.xn, h1, self.epsilon)
                    x1_k, y1_k = ode_methods_runge_kuta.runge_kutta_4()
            else:
                h1 /= 2
                ode_methods_runge_kuta = ODEMethods(self.func, self.y0, self.x0, self.xn, h1, self.epsilon)

        print(f"По правилу рунге для метода Рунге-Кутта h = {h1}")
        steps1 = int((self.xn - self.x0)/h1) + 1
        y = np.zeros((steps1, len(self.y0)))
        y[:4] = ode_methods_runge_kuta.runge_kutta_4()[1][:4]
        x = np.linspace(self.x0, self.xn, steps1)

        for i in range(4, steps1):
            y_pred = y[i-4] + 4 * h1 / 3 * (2 * self.func(x[i-3], y[i-3])
                                                - self.func(x[i-2], y[i-2])
                                                + 2 * self.func(x[i-1], y[i-1]))
            f_pred = self.func(x[i], y_pred)
            while True:
                y_corr = y[i-2] + h1 / 3 * (self.func(x[i-2], y[i-2])
                                                + 4 * self.func(x[i-1], y[i-1])
                                                + f_pred)
                error = np.abs(y_corr - y_pred)
                if error < self.epsilon:
                    y[i] = y_corr
                    break
                else:
                    y_pred = y_corr
                    f_pred = self.func(x[i], y_pred)
        return x, y

    def get_exact_solution(self, x):
        f = wrap(self.func)
        arr = scipy.integrate.odeint(f, self.y0, x)
        return np.array([x[0] for x in arr])

def func1(x, y):
    return y + (1 + x) * y**2

def func2(x, y):
    return -y + (1 + x) * y**2

def func3(x, y):
    return x * y

def wrap(f):
    def swapped(y, x):
        return f(x, y)
    return swapped

def runge_rule(func, y0, x0, xn, h, epsilon):
    h1 = h
    ode_methods_euler = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
    x_euler, y_euler = ode_methods_euler.euler()

    ode_methods_euler = ODEMethods(func, y0, x0, xn, h1 / 2, epsilon=epsilon)
    x1_euler, y1_euler = ode_methods_euler.euler()
    for i in range(int((xn - x0) / h) + 1):
        if (abs(y_euler[i] - y1_euler[i]) / (2 - 1) > epsilon):
            while abs(y_euler[i] - y1_euler[i]) / (2 - 1) > epsilon:
                ode_methods_euler = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
                x_euler, y_euler = ode_methods_euler.euler()
                h1 /= 2
                ode_methods_euler = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
                x1_euler, y1_euler = ode_methods_euler.euler()
        else:
            h1 /= 2
            ode_methods_euler = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)

    euler_error = abs(y_euler[-1] - y1_euler[-1]) / (2 - 1)
    print(f"По правилу рунге для метода Эйлера h = {h1}")

    h1 = h
    ode_methods_euler_improved = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
    x_euler_improved, y_euler_improved = ode_methods_euler_improved.euler_improved()

    ode_methods_euler_improved = ODEMethods(func, y0, x0, xn, h1 / 2, epsilon=epsilon)
    x1_euler_improved, y1_euler_improved = ode_methods_euler_improved.euler_improved()

    for i in range(int((xn-x0)/h) + 1):
        if (abs(y_euler_improved[i] - y1_euler_improved[i]) / (2 ** 2 - 1) > epsilon):
            while abs(y_euler_improved[i] - y1_euler_improved[i]) / (2 ** 2 - 1) > epsilon:
                ode_methods_euler_improved = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
                x_euler_improved, y_euler_improved = ode_methods_euler_improved.euler_improved()
                h1 /= 2
                ode_methods_euler_improved = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)
                x1_euler_improved, y1_euler_improved = ode_methods_euler_improved.euler_improved()
        else:
            h1 /= 2
            ode_methods_euler_improved = ODEMethods(func, y0, x0, xn, h1, epsilon=epsilon)

    euler_improved_error = abs(y_euler_improved[-1] - y1_euler_improved[-1]) / (2 ** 2 - 1)
    print(f"По правилу рунге для модифицированного метода Эйлера h = {h1}")

    return ode_methods_euler, ode_methods_euler_improved, euler_error, euler_improved_error



def main():
    print("Выберите уравнение:")
    print("1. y' = y + (1 + x)y^2")
    print("2. y' = -y + (1 + x)y^2")
    print("3. y' = x * y")
    choice = int(input("Введите номер уравнения (1, 2, 3): "))

    if choice == 1:
        func = func1
    elif choice == 2:
        func = func2
    elif choice == 3:
        func = func3
    else:
        print("Неверный выбор")
        return

    y0 = [float(input("Введите начальное условие y0: "))]
    x0 = float(input("Введите начальное значение x0: "))
    xn = float(input("Введите конечное значение xn: "))
    h = float(input("Введите шаг h: "))
    epsilon = float(input("Введите точность epsilon: "))

    ode_methods_euler, ode_methods_euler_improved, euler_error, euler_improved_error = runge_rule(func, y0, x0, xn, h, epsilon=epsilon)

    x_euler, y_euler = ode_methods_euler.euler()
    ode_methods_euler.exact_solution = ode_methods_euler.get_exact_solution(x_euler)

    x_euler_improved, y_euler_improved = ode_methods_euler_improved.euler_improved()
    ode_methods_euler_improved.exact_solution = ode_methods_euler_improved.get_exact_solution(x_euler_improved)

    ode_methods_milne = ODEMethods(func, y0, x0, xn, h, epsilon=epsilon)
    x_milne, y_milne = ode_methods_milne.milne()
    ode_methods_milne.exact_solution = ode_methods_milne.get_exact_solution(x_milne)

    print("Вывести значения с изначальным шагом или изменённым?")
    choice_h_out = int(input("1 - изначальный; 2 - изменённый: "))

    if choice_h_out == 1:
        h_out = 1
    elif choice_h_out == 2:
        h_out = 2
    else:
        print("Неверный выбор")
        return

    table = PrettyTable()
    table.field_names = ["i", "x", "Euler", "Exact"]

    counter = 0
    for i in range(len(x_euler)):
        if (h_out == 1 and x_euler[i] == (x0 + h * counter)):
            counter += 1
            table.add_row(
                [i, x_euler[i], y_euler[i, 0], ode_methods_euler.exact_solution[i]])
        elif (h_out == 2):
            table.add_row(
                [i, x_euler[i], y_euler[i, 0], ode_methods_euler.exact_solution[i]])

    print("\nТаблица значений для метода Эйлера:")
    print(table)

    table = PrettyTable()
    table.field_names = ["i", "x", "Euler Improved", "Exact"]

    counter = 0
    for i in range(len(x_euler_improved)):
        if (h_out == 1 and x_euler_improved[i] == x0 + h * counter):
            counter += 1
            table.add_row(
                [i, x_euler_improved[i], y_euler_improved[i, 0], ode_methods_euler_improved.exact_solution[i]])
        elif (h_out == 2):
            table.add_row(
                [i, x_euler_improved[i], y_euler_improved[i, 0], ode_methods_euler_improved.exact_solution[i]])

    print("\nТаблица значений для модифицированного метода Эйлера:")
    print(table)

    table = PrettyTable()
    table.field_names = ["i", "x", "Milne", "Exact"]

    counter = 0
    for i in range(len(x_milne)):
        if (h_out == 1 and x_milne[i] == x0 + h * counter):
            counter += 1
            table.add_row(
                [i, x_milne[i], y_milne[i, 0], ode_methods_milne.exact_solution[i]])
        elif (h_out == 2):
            table.add_row(
                [i, x_milne[i], y_milne[i, 0], ode_methods_milne.exact_solution[i]])

    print("\nТаблица значений для метода Милна:")
    print(table)

    milne_error = np.max(np.abs(y_milne[:, 0] - ode_methods_milne.exact_solution))

    print(f"\nТочность метода Эйлера: {euler_error[0]}")
    print(f"Точность метода улучшенного Эйлера: {euler_improved_error[0]}")
    print(f"Точность метода Милна: {milne_error}")

    # Plotting
    plt.figure(figsize=(12, 6))
    plt.plot(x_euler, y_euler[:, 0], label="Euler", color="blue")
    plt.plot(x_euler_improved, y_euler_improved[:, 0], label="Euler Improved", color="orange")
    plt.plot(x_milne, y_milne[:, 0], label="Milne", color="red")
    plt.plot(x_euler,ode_methods_euler.exact_solution, label="Exact Solution", color="black", linestyle="dashed")
    plt.xlabel("x")
    plt.ylabel("y")
    plt.legend()
    plt.title("Численные решения ОДУ")
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()
