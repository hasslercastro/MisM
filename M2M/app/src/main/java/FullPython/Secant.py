from sympy import *
from math import e
from prettytable import PrettyTable

t = PrettyTable(['Iteration','Xn','fxn','Error'])
x =  Symbol('x')

def secant(tolerance,x0,x1,niter,function):
    fx0 = function.evalf(subs={x:x0})
    if fx0 == 0:
        print("{0} Is a root".format(x0))
    else:
        fx1 = function.evalf(subs={x:x1})
        counter = 0
        error = tolerance + 1
        den = fx1 - fx0
        t.add_row([counter,x0,fx0,'--'])
        counter += 1
        t.add_row([counter,x1,fx1,'--'])
        while error > tolerance and fx1 != 0 and den != 0 and counter < niter:
            x2 = x1 - (fx1*(x1-x0))/den
            error = abs((x2-x1))
            x0 = x1
            fx0 = fx1
            x1 = x2
            fx1 = function.evalf(subs={x:x1})
            den = fx1 - fx0
            counter += 1
            t.add_row([counter,x1,fx1,error])
        print(t)

        if fx1 == 0:
            print("{0} Is a root".format(x1))
        elif error < tolerance:
            print("{0} Is an approximation".format(x1))
        elif den == 0:
            print("Is a posible multiple root")
        else:
            print("failed on {0} iterations".format(niter))

#########################################################
function  = (exp(-x)) + (sqrt(x))*(ln((x**2)+1)) - (x**2)
tol = (10**(-3)) 
x0 = 1
x1 = 1.2
niter = 100
#########################################################
secant(tol,x0,x1,niter,function)