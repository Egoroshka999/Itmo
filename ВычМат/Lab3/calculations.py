from math import sin

f1 = lambda x: x ** 2  # Возведение в квадрат
f2 = lambda x: x ** 3  # Возведение в куб
f3 = lambda x: sin(x)  # Вычисление синуса
f4 = lambda x: x**3 + 2*x**2 - 3*x - 12  # Вычисление значения многочлена

def mod1(a, h, i):
    return a + i*h

def mod2(a, h, i):
    return a + (i+1)*h

def mod3(a, h, i):
    return a + (i+0.5)*h

def rungeRule(f, a, b, n, e, method, k, mod=None):
    I_n = method(f, a, b, n, mod) if mod is not None else method(f, a, b, n)
    #print("n= ",n , " I= ", I_n)
    n *= 2
    I_2n = method(f, a, b, n, mod) if mod is not None else method(f, a, b, n)
    #print("n= ", n, " I= ", I_2n)
    while abs(I_2n - I_n) / (2 ** k - 1) > e:
        if (n >= 8388608):
            raise Exception('Интеграл расходится')
        I_n = I_2n
        #print("n= ", n, " I= ", I_n)
        n *= 2
        I_2n = method(f, a, b, n, mod) if mod is not None else method(f, a, b, n)

    #print(abs(I_n - I_2n))
    return I_2n, n



def rectangleMethod(f, a, b, n, mod):
    h = (b - a) / n
    total = 0.0

    for i in range(n):
        if mod == 1:
            x = mod1(a, h, i)
        elif mod == 2:
            x = mod2(a, h, i)
        elif mod == 3:
            x = mod3(a, h, i)
        else:
            continue

        try:
            total += f(x)
        except Exception as e:
            total += 0

    return total * h


def trapezoidMethod(f, a, b, n):
    h = (b - a) / n
    total = 0


    try:
        total += 0.5 * f(a)
    except Exception as e:
        total += 0

    try:
        total += 0.5 * f(b)
    except Exception as e:
        total += 0

    for i in range(1, n):
        x = a + i * h
        try:
            total += f(x)
        except Exception as e:
            total += 0

    return total * h


def simpsonMethod(f, a, b, n):
    h = (b - a) / n
    total = 0
    try:
        total += f(a)
    except Exception:
        total += 0

    try:
        total += f(b)
    except Exception:
        total += 0

    for i in range(1, n, 2):
        x = a + i * h
        try:
            total += 4 * f(x)
        except Exception:
            total += 0

    for i in range(2, n - 1, 2):
        x = a + i * h
        try:
            total += 2 * f(x)
        except Exception:
            total += 0  # Пропускаем невычисляемые точки

    return total * h / 3