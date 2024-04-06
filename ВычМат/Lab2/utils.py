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

def chooseEqualisation():
    print("Уравнения:")
    print("1 - f(x) = x^3+2.28x^2-1.934x-3.907")
    print("2 - f(x) = 5sin(x)+0.2")
    print("3 - f(x) = 0.3x^2+cos(x^2)-7")
def chooseSystem():
    print("Системы уравнений:")
    print("1 - tg(xy+0.2)=x^2")
    print("1 - x^2+2y^2=1")
    print("2 - sin(x+y)-1.2x=0.2")
    print("2 - x^2+2y^2=2")

def chooseMethod():
    print("1 - Метод половинного деления")
    print("2 - Метод секущих")
    print("3 - Метод простой итерации")

def printSystemInfo(x,y,n):
    print("Вектор неизвестных: ",x,y)
    print("Количество итераций: ",n)

def printSolve(x,y,n):
    print("Корень: ", x,"Значение в корне:", y)
    print("Количество итераций: ", n)


