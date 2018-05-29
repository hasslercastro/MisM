import numpy as np
import sympy as sp
x = sp.Symbol('x')
def findL(points):
    L = [0]*len(points)
    for i in range(len(L)):
        numerator = 1.0
        denominator = 1.0
        for j in range(len(points)):
            if(not (i == j)):
                numerator *= x - points[j]
                denominator *= points[i] - points[j]

        L[i] = numerator.expand() / denominator
    return L

def get_polinomio(points, f):
    polinomio =  0.0
    L = findL(points)
    for i in range(len(L)):
        polinomio += (L[i]*f[i])
    return polinomio.simplify()

def main():
    x = [-1, 1, 2, 4]
    f = [7, -1, -8, 2]
    print(get_polinomio(x, f))
main()