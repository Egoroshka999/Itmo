def inputInt(message, errorMessage):
    while True:
        try:
            num = int(input(message))
            break
        except ValueError:
            print(errorMessage)
    return num

def inputFloat(message, errorMessage):
    while True:
        try:
            num = float(input(message))
            break
        except ValueError:
            print(errorMessage)
    return num

def chooseIntegral():
    print("Функции:")
    print("1 - f(x) = x^2")
    print("2 - f(x) = x^3")
    print("3 - f(x) = sin(x)")
    print("4 - f(x) = x^3 + 2x^2 - 3x - 12")

def chooseMethod():
    print("1 - Метод прямоугольников")
    print("2 - Метод трапеций")
    print("3 - Метод Симпсона")

def chooseModification():
    print("1 - левые")
    print("2 - правые")
    print("3 - средние")

def printSolve(x,n):
    print("Значение интеграла: ", x)
    print("Число разбиений: ", n)

#----------------------------------------------
def chooseIntegralDop():
    print("Функции:")
    print("1 - f(x) = 1/x")
    print("2 - f(x) = 1/(1-x^2)^0.5")
    print("3 - f(x) = 1/(x-1)")
    print("4 - f(x) = 1/(2*x-x^2)^0.5")
