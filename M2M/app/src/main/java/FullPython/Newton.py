from sympy import *
from math import e
from prettytable import PrettyTable

t = PrettyTable(['Iteration','Xn','fxn','dfx','Error'])

x =  Symbol('x')


def newton(tolerance,x0,niter,function):
    fx = function.evalf(subs={x:x0})
    dfx = function.diff(x).evalf(subs={x:x0})
    counter = 0
    error = tolerance +1
    t.add_row([counter,x0,fx,dfx,'--'])
    while error > tolerance and fx != 0 and dfx != 0 and counter < niter:
        x1 = (x0) - (fx/dfx)
        fx = function.evalf(subs={x:x1})
        dfx = function.diff(x).evalf(subs={x:x1})
        error = abs((x1-x0))
        x0 = x1
        counter += 1
        t.add_row([counter,x0,fx,dfx,error])
    print(t)
    if fx == 0:
        print("{0} Is a root".format(x0))
    elif error < tolerance :
        print("{0} Is an approximation".format(x1))
    elif dfx == 0:
        print("Is a posible multiple root")
    else:
        print("failed on {0} iterations".format(niter))

########################################################
func = (x**3)-(x**2)-(x)+(1)+(sin(x-1)**2)
tolerance = 10**(-7)
x0 = (-0.5)
niter = 1000
########################################################

newton(tolerance,x0,niter,func)
