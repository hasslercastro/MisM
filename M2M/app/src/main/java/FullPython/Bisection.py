from sympy import *
from math import e
from prettytable import PrettyTable

x = Symbol('x')

t = PrettyTable(['Iteration', 'Xi', 'Xs', 'Xm', 'fxm', 'Error'])


def BisectionMethod(xi, xs, tolerance, niters):
    fxi = f(xi)
    #print (xi)
    fxs = f(xs)
    #print (fxs)
    if fxi == 0:
        return (fxi, " is a root")
    elif fxs == 0:
        return (fxs, " is a root")
    elif fxi*fxs < 0:
        xm = (xi + xs)/2
        fxm = f(xm)
        counter = 1
        error = tolerance + 1
        t.add_row([counter, xi, xs, xm, fxm, '--'])
        while error > tolerance and fxm != 0 and counter < niters:
            if fxi*fxm < 0:
                xs = xm
                fxs = f(xm)
            else:
                xi = xm
                fxi = f(xm)
            xaux = xm
            xm = (xs + xi)/2
            fxm = f(xm)
            error = abs(xm-xaux)
            counter = counter+1
            t.add_row([counter, xi, xs, xm, fxm, error])
        print(t)
        if fxm == 0:
            print("The root is {0}".format(xm))
        elif error < tolerance:
            return (str(xm)+" is an aproximation with an error of: "+str(error))+" and number of iterations: "+str(counter)
        else:
            return ("failed in "+str(niters)+" iterations")
    else:
        return ("Wrong interval")


##############################################
function = exp((-x**2)+1) - (4*x**3) +(25)
a = 1
b = 2
tolerance = 0.001
iterations = 100
##############################################


def f(x0):
    return function.evalf(subs={x: x0})


print (BisectionMethod(a, b, tolerance, iterations))
