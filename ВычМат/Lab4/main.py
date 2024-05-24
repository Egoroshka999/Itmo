from calculations import *
from input import *
from prettytable import PrettyTable

mode = ask_input_data()

if mode == 1:
    points = get_points_file()
elif mode == 2:
    points = get_points_console()

# Точки -------------------------------------------------------------
print(f"Полученные точки: ")

table = PrettyTable()
table.field_names = ["x", "y"]

for row in points:
    table.add_row(row)

print(table)
# --------------------------------------------------------------------

linear_func, linear_str_func, linear_err, linear_squad_err, linear_k_det = linear_approximate(points)
print(f"Линейной аппроксимацией получена функция: {linear_str_func}, S = {round(sum(linear_err), 3)}, sigma = {round(linear_squad_err, 3)}")
print(r_check(linear_k_det))
print()

squad_func, squad_str_func, squad_err, squad_squad_err, squad_k_det = squad_approximate(points)
print(f"Квадратичной аппроксимацией получена функция: {squad_str_func}, S = {round(sum(squad_err), 3)}, sigma = {round(squad_squad_err, 3)}")
print(r_check(squad_k_det))
print()

cub_func, cub_str_func, cub_err, cub_squad_err, cub_k_det = qub_approximate(points)
print(f"Кубической аппроксимацией получена функция: {cub_str_func}, S = {round(sum(cub_err), 3)}, sigma = {round(cub_squad_err, 3)}")
print(r_check(cub_k_det))
print()

exp_func, exp_str_func, exp_err, exp_squad_err, exp_k_det = exp_approximate(points)
if exp_func is None:
    print("Нет ни одной точки в области определения экспоненциальной функции")
    exp_squad_err = math.inf
else:
    print(f"Экспоненциальной аппроксимацией получена функция: {exp_str_func}, S = {round(sum(exp_err), 3)}, sigma = {round(exp_squad_err, 3)}")
    print(r_check(exp_k_det))
print()

log_func, log_str_func, log_err, log_squad_err, log_k_det = ln_approximate(points)
if log_func is None:
    print("Нет ни одной точки в области определения логарифмический функции")
    log_squad_err = math.inf
else:
    print(f"Логарифмической аппроксимацией получена функция: {log_str_func}, S = {round(sum(log_err), 3)}, sigma = {round(log_squad_err, 3)}")
    print(r_check(log_k_det))
print()

deg_func, deg_str_func, deg_err, deg_squad_err, deg_k_det = degree_approximate(points)
if deg_func is None:
    print("Нет ни одной точки в области опредения степенной функции")
    deg_squad_err = math.inf
else:
    print(f"Степенной апроксимацией получена функция: {deg_str_func}, S = {round(sum(deg_err), 3)}, sigma = {round(deg_squad_err, 3)}")
    print(r_check(deg_k_det))
print()

# Словарь для хранения ошибок и типов аппроксимаций
approximation_errors = {
    "линейная": linear_squad_err,
    "квадратичная": squad_squad_err,
    "кубическая": cub_squad_err,
    "экспоненциальная": exp_squad_err,
    "логарифмическая": log_squad_err,
    "степенная": deg_squad_err
}

# Поиск минимального среднеквадратичного отклонения и соответствующей аппроксимации
min_approximation = min(approximation_errors, key=approximation_errors.get)
min_r = approximation_errors[min_approximation]

print(f"Минимальное среднеквадратичное отклонение: {round(min_r, 3)}")
print(f"Лучшая аппроксимация: {min_approximation}")

ploting(points, linear_func, squad_func, cub_func, exp_func, log_func, deg_func)
