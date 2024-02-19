def inputMatrixDimensions():
    n = int(input("Введите размерность матрицы (n): "))
    return n

def inputMatrixDimensionFromFile(f):
    n = int(f.readline())
    return n
def inputMatrixAndVector(n):
    A = []
    b = []
    print("Введите коэффициенты матрицы и вектор свободных членов:")
    for i in range(n):
        row = list(map(float, input(f"Строка {i + 1} (через пробел): ").split()))
        A.append(row[:-1])
        b.append(row[-1])
    return A, b

def inputMatrixAndVectorFromFile(n, f):
    A = []
    b = []
    for i in range(n):
        row = list(map(float, f.readline().split()))
        A.append(row[:-1])
        b.append(row[-1])
    return A, b


def inputPrecisionAndMaxIterations():
    eps = float(input("Введите точность: "))
    max_iter = int(input("Введите максимальное число итераций: "))
    return eps, max_iter

def inputPrecisionAndMaxIterationsFromFile(f):
    eps = float(f.readline())
    max_iter = int(f.readline())
    return eps, max_iter


def isDiagonallyDominant(row, index):
    return abs(row[index]) > sum([abs(row[j]) for j in range(len(row)) if j != index])

def toDiagonalDominance(A, b):
    n = len(A)
    for i in range(n):
        if not isDiagonallyDominant(A[i], i):
            for j in range(i + 1, n):
                if isDiagonallyDominant(A[j], i):
                    A[i], A[j] = A[j], A[i]  # Swap rows in A
                    b[i], b[j] = b[j], b[i]  # Swap elements in b
                    break
            else:
                print("Достичь диагонального преобладания невозможно.")
                return False
    return True


def prepareMatrixAndVector(A, b):
    n = len(A)
    C = [[0 for _ in range(n)] for _ in range(n)]
    d = [0 for _ in range(n)]
    for i in range(n):
        d[i] = b[i] / A[i][i]
        for j in range(n):
            if i != j:
                C[i][j] = -A[i][j] / A[i][i]
            else:
                C[i][j] = 0
    return C, d

def printMatrixAndVector(A, b, n):
    for i in range(n):
        print("| ", end="")
        for j in range(n):
            print(A[i][j], end=" ")
        print(" | ", end="")
        print(b[i], end="")
        print(" |")

def printUnknownVectors(C, d, n):
    for i in range(n):
        # Составляем строку для i-го уравнения
        equation_parts = [f"{C[i][j]:.4f}*x{j + 1}" for j in range(n) if C[i][j] != 0]
        equation = " + ".join(equation_parts)
        print(f"x{i + 1} = {d[i]:.4f} + {equation}")

def simpleIterationMethod(C, d, eps, max_iter):
    n = len(C)
    x = [0] * n  # Начальное приближение
    x_new = d.copy()  # Используем вектор d для первого приближения

    for k in range(0, max_iter):
        max_diff = 0  # Для отслеживания максимальной разницы между приближениями
        for i in range(n):
            x_new[i] = d[i] + sum(C[i][j] * x[j] for j in range(n))
            max_diff = max(max_diff, abs(x_new[i] - x[i]))
            x_new_rounded = [f"{num:.4f}" for num in x_new]
        if (k == 0):
            print(f"Приближение на итерации {k}: {x_new_rounded}")
        else:
            print(f"Приближение на итерации {k}: {x_new_rounded}, погрешность: {max_diff:.5f}")
        if max_diff < eps:
            x = [round(x, 0) for x in x]
            print(f"Следовательно точное решение: {x}")
            print(f"Достигнуто за {k} итераций")
            return x_new, k
        x = x_new.copy()
    return x, max_iter

def main():
    print("Ввод данных с клавиатуры - 0")
    print("Ввод данных из файла - 1")
    channel = int(input())
    if (channel == 0):
        n = inputMatrixDimensions()
        A, b = inputMatrixAndVector(n)
        eps, max_iter = inputPrecisionAndMaxIterations()
    elif (channel == 1):
        print("Введите имя файла")
        filename = input()
        f = open(filename, "r")
        n = inputMatrixDimensionFromFile(f)
        A, b = inputMatrixAndVectorFromFile(n, f)
        eps, max_iter = inputPrecisionAndMaxIterationsFromFile(f)

    print("Исходная матрица и вектор свободных членов:")
    printMatrixAndVector(A, b, n)

    if not toDiagonalDominance(A, b):
        print("Не удалось достичь диагонального преобладания. Продолжение решения может не привести к сходимости.")
        exit(0)

    print("Матрица после приведения к условию преобладания диоганальных элементов:")
    printMatrixAndVector(A, b, n)

    C, d = prepareMatrixAndVector(A, b)
    print("Вектора неизвестных:")
    printUnknownVectors(C, d, n)

    print("Преобразованная система (матрица C и вектор d):")
    printMatrixAndVector(C, d, n)

    simpleIterationMethod(C, d, eps, max_iter)

if __name__ == "__main__":
    main()