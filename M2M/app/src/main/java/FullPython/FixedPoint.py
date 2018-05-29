from sympy import *
from prettytable import PrettyTable

t = PrettyTable(['Iteration', 'Xn', 'fxn', 'Error'])

x = Symbol('x')


def FixedPoint(tolerance, xa, niters):
    fx = f(xa)
    counter = 0
    error = tolerance + 1
    t.add_row([counter, xa, fx, '--'])
    while fx != 0 and error > tolerance and counter < niters:
        xn = g(xa)
        fx = f(xn)
        error = abs(((xn-xa)))
        xa = xn
        counter = counter + 1
        t.add_row([counter, xn, fx, error])
    print(t)
    if fx == 0:
        print(str(xa)+"Is root")
    elif error < tolerance:
        print(str(xa)+" Is an aproximation with a tolerance of : "+str(tolerance))
    else:
        print('Failed in  {0} niters'.format(niters))


############################################
functionF = (x**3) + (4*x**(2))-10
functionG = sqrt((10)/(x+4))
tolerance = 10**(-8)
xa = 1.5
niters = 100
#############################################


def f(arg):
    return functionF.evalf(subs={x: arg})


def g(arg):
    return functionG.evalf(subs={x: arg})


FixedPoint(tolerance, xa, niters)
