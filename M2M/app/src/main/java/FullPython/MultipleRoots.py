from sympy import *
from math import e
from prettytable import PrettyTable

t = PrettyTable(['Iteration', 'Xn', 'fxn', 'dfx', 'ddfx', 'Error'])

x = Symbol('x')


def multiple_roots(tolerance, x0, niter, function):
    fx = function.evalf(subs={x: x0})
    dfx = function.diff(x).evalf(subs={x: x0})
    ddfx = function.diff(x).diff(x).evalf(subs={x: x0})
    counter = 0
    error = tolerance + 1
    t.add_row([counter, x0, fx, dfx, ddfx, '--'])
    while error > tolerance and fx != 0 and dfx != 0 and counter < niter:
        x1 = (x0) - ((fx*dfx)/((dfx**2)-(fx*ddfx)))
        fx = function.evalf(subs={x: x1})
        dfx = function.diff(x).evalf(subs={x: x1})
        ddfx = function.diff(x).diff(x).evalf(subs={x: x1})
        error = abs((x1-x0))
        x0 = x1
        counter += 1
        t.add_row([counter, x0, fx, dfx, ddfx, error])
    print(t)
    if fx == 0:
        print("{0} Is a root".format(x0))
    elif error < tolerance:
        print("{0} Is an approximation".format(x1))
    elif dfx == 0:
        print("Is a posible multiple root")
    else:
        print("failed on {0} iterations".format(niter))


########################################################
func = (x**4) - (18*(x**2)) +81
tolerance = 10**(-8)
x0 = -2.5
niter = 100
########################################################
multiple_roots(tolerance, x0, niter, func)
