from prettytable  import PrettyTable
from math import *
from threading import Thread
import time

def make_table(n): #Create method table
    table = PrettyTable()
    fields = ["i"]
    for i in range(n):
        fields.append("x{0}".format(i+1))
    fields.append("Max &")
    table.field_names = fields
    return table

def parallel_aux_jacobi(x1, i, a, b,x0):
    summ = 0
    time.sleep(0.1)
    print("Thread {0}".format(i))
    for j in range(0, len(a)):
        if j != i:
            summ += a[i][j]*x0[j]
    x1[i] = (b[i] - summ) / a[i][i]

def calculate_new_Jacobi(x0,a,b):
    result = [0]*len(b)
    threads = [None]*len(b)
    for i in range(0, len(a)):
        threads[i] = Thread(target=parallel_aux_jacobi, args=(result,i, a, b,x0))
        threads[i].start()
    for i in range(len(threads)):
        threads[i].join()
    return result

def list_subtraction(a,b): #Substract the elements between two lists
    result = []
    for i in range(len(a)):
        result.append(abs(a[i] - b[i]))
    return result

def make_row(i,x0, dispersion):#Create a row who will be added to the table
    row = [i]
    for k in range(len(x0)):
            row.append(x0[k])
    row.append(dispersion)
    return row

def jacobi(a, b, x0, tol, niter):
    counter = 0
    dispersion = tol + 1
    x1 = []
    table = make_table(len(x0))
    table.add_row(make_row(counter, x0, "----"))
    while (dispersion > tol) and (counter < niter):
        x1 = calculate_new_Jacobi(x0, a, b)
        dispersion = max(list_subtraction(x1, x0))  # Norm
        x0 = x1
        counter += 1
        table.add_row(make_row(counter, x0, dispersion))
    if dispersion < tol:
        return table
    else:
        return "Failed"
        
def main():
    A = [[45, 13, -4, 8],[-5, -28, 4, -14],[9, 15, 63, -7],[2, 3, -8, -42]]
    b = [-25, 82, 75, -43]
    x0 = [2, 2, 2, 2]
    print(jacobi(A, b, x0, 10**-5, 100))
main()
