from sympy import *
import math as m
from prettytable import PrettyTable

x = Symbol('x')

t = PrettyTable(['iteration','interval'])

def IncrementalSearch(tolerance,x0,delta,niter,function):
    fx0 = function.evalf(subs={x:x0})

    if m.fabs(fx0) < tolerance:
        print( str(x0)+" Is root")
    else:
        x1 = x0 + delta
        cont = 1
        fx1 = function.evalf(subs={x:x1})
        t.add_row([cont,'['+str(x1)+','+str(x0)+']'])
        while fx0 * fx1 > tolerance and cont< niter:
            x0 = x1
            fx0 = fx1
            x1 = x0 + delta
            fx1 = function.evalf(subs={x:x1})
            cont = cont + 1
            t.add_row([cont,'['+str(x0)+','+str(x1)+']'])
        print(t)
        if m.fabs(fx1) < tolerance:
            print (str(x1)+" Is root")
        elif fx0 * fx1 < tolerance:
            print ("There is a root between "+str(x0)+" and " + str(x1))
        else:
            print ("failed in  "+ str(niter)+ " iterations")

######################################
tolerance = 10**(-9)
x0 = -5
delta = 1
niter = 2000
function = (x**3) + (4*(x**(2)))-9.47
######################################

IncrementalSearch(tolerance,x0,delta,niter,function)
