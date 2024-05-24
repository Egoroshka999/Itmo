from utils import *
from lab3dop.calculations import *

def solveIntegral():
    print("Выберите интеграл, который вы хотите вычислить")
    chooseIntegral()
    number = inputInt("Введите номер: ", "Номер должен быть представлен числом")
    if number == 1:
        f = f1
    elif number == 2:
        f = f2
    elif number == 3:
        f = f3
    elif number == 4:
        f = f4
    else:
        print("Вы ввели не существующюю функцию, попробуйте ещё раз")

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
    if m == 1:
        print("Выберите модификацию:")
        chooseModification()
        mod = inputInt("Введите номер: ", "Номер должен быть представлен числом")
        if (mod != 1 and mod != 2 and mod !=3):
            print("Неизвестная модификация")
            exit(1)
        if (mod == 1 or mod == 2):
            k = 1
        if (mod == 3):
            k = 2
        value, intervals = rungeRule(f,a,b,n,e,rectangleMethod,k,mod)
    elif m == 2:
        value, intervals = rungeRule(f,a,b,n,e,trapezoidMethod,2)
    elif m == 3:
        value, intervals = rungeRule(f,a,b,n,e,simpsonMethod,4)
    else:
        print("Вы ввели не существующий номер метода, попробуйте ещё раз")

    printSolve(value, intervals)


if __name__ == '__main__':
    solveIntegral()


