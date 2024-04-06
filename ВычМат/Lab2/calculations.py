from math import cos, sin,tan
from prettytable import PrettyTable

def equation1(x): return x**3 + 2.28*x**2 - 1.934*x - 3.907
def diffEquation1(x): return 3*x**2 + 4.56*x - 1.934

def equation2(x): return 5*sin(x) + 0.2
def diffEquation2(x):return 5*cos(x)

def equation3(x): return 0.3*x**2 + cos(x**2) - 7
def diffEquation3(x): return 0.6*x -2 * x * sin(x**2)


def lambCount(a, b, diffEquation): return -1 / max(abs(diffEquation(a)), abs(diffEquation(b)))

def phiEquation(lamb, x, equation): return x + lamb * equation(x)
def diffPhiEquation(lamb, x, diffEquation): return 1 + lamb * diffEquation(x)

def qCount(a, b,lamb, diffEquation):
    qa = abs(diffPhiEquation(lamb, a, diffEquation))
    qb = abs(diffPhiEquation(lamb, b, diffEquation))
    # print(qa,qb)
    return max(qa, qb)


def methogHalfDiv(a, b, e, equation):
    n = 0

    table = PrettyTable(['n', 'a', 'b', 'x', 'f(a)', 'f(b)', 'f(x)', '|a - b|'])

    while True:
        x = (a + b) / 2
        table.add_row([n, a, b, x, equation(a), equation(b), equation(x), abs(a - b)])
        if abs(a - b) <= e and abs(equation(x)) <= e:
            break
        if equation(a) * equation(x) > 0:
            a = x
        else:
            b = x
        n += 1


    print(table)
    x = (a + b) / 2
    return x, equation(x), n


def methodCut(a, b, e, equation):
    #Проверить сходимость
    x0 = b
    x1 = a
    n = -1
    table = PrettyTable(['n', 'xi-1', 'xi', 'xi+1', 'f(xi+1)', '|xi+1 - xi|'])

    while True:
        n += 1
        x2 = x1 - ((x1 - x0) / (equation(x1) - equation(x0))) * equation(x1)
        table.add_row([n, x0, x1, x2, equation(x2), abs(x2 - x1)])

        if abs(x2 - x1) <= e and abs(equation(x2)) <= e:
            break
        x0 = x1
        x1 = x2
    print(table)
    return x2, equation(x2), n


def methodSimpIter(a, b, e, equation, diffEquation):
    table = PrettyTable(['n','xi', 'xi+1', 'phi(xi+1)', 'f(xi+1)', '|xi+1 - xi|'])
    lamb = lambCount(a, b, diffEquation)
    q = qCount(a, b,lamb, diffEquation)  #коэффициент Липшица или коэффициент сжатия
    print('q=',q)
    if (q > 1):
        print('Сходимости нет, но продолжаем вычисления')
    print(diffPhiEquation(lamb, a, diffEquation))
    print(diffPhiEquation(lamb, b, diffEquation))
    r = 500
    n = -1
    x = (a + b) / 2
    while r>0:
        n += 1
        x1 = phiEquation(lamb, x, equation) #xi+1 = phi(xi)= ....
        table.add_row([n, x, x1, phiEquation(lamb, x1, equation), equation(x1), abs(x1 - x)])
        if abs(x1 - x) <= e and equation(x1) <= e:
            break
        x = x1
        r -= 1
    print(table)
    return x1, equation(x1), n+1

def dfdx1(x,y): return y/cos(x*y+0.2)**2-2*x
def dfdy1(x,y): return x/cos(x*y+0.2)**2
def dgdx1(x,y): return 2*x
def dgdy1(x,y): return 4*y
def f1(x,y): return tan(x*y+0.2)-x**2
def g1(x,y): return x*x+2*y*y-1


def f2(x,y): return sin(x+y)-1.2*x-0.2
def g2(x,y): return x*x+2*y*y-2
def dfdx2(x,y):return cos(x+y)-1.2
def dfdy2(x,y):return cos(x+y)
def dgdx2(x,y):return 2*x
def dgdy2(x,y):return 4*y


def cramerMethod(x,y,a1,a2,a3,a4,f,g):
    d = a1(x,y)*a4(x,y)-a2(x,y)*a3(x,y)
    dx = -f(x,y)*a4(x,y)-a2(x,y)*(-g(x,y))
    dy = a1(x,y)*(-g(x,y))-a3(x,y)*(-f(x,y))
    deltaX = dx/d
    deltaY = dy/d
    return deltaX,deltaY

def newtonMethod(e,x,y,f,g,dfdx,dfdy,dgdx,dgdy):
    x0=x
    y0=y
    n=-1
    while True:
        n+=1
        deltaX, deltaY = cramerMethod(x0,y0,dfdx,dfdy,dgdx,dgdy,f,g)
        x1 = x0 + deltaX
        y1 = y0 + deltaY
        print(x1, y1)
        if abs(x1-x0)<=e and abs(y1-y0)<=e:
            # print(abs(x1-x0),abs(y1-y0))
            break
        x0=x1
        y0=y1
    return x1,y1,n

def searchSolves(a,b,h,equation):
    i=a
    m=[]
    while i<b:
        if equation(i)*equation(i+h)<=0:
            m.append((i,i+h))
        i+=h
    return m