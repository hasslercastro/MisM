import numpy as np
from sympy import sympify
import time
from threading import Thread

class LinearSpline():

    def expandParams(self,X, Y):
        n = len(X)
        new_X = np.zeros(n)
        new_Y = np.zeros(n)

        for i in range(n):
            new_X[i] = X[i]
            new_Y[i] = Y[i]

        points = np.array((new_X, new_Y)).T
        return points

    def aux_compute(self, i, points, splines):
        time.sleep(0.1)
        print("Thread {0}".format(i))
        segment = self.computeSegment(points[i], points[i+1])
        splines[i] = segment

    def compute(self, params):
        X = params["X"]
        Y = params["Y"]

        points = self.expandParams(X, Y)

        points = np.array(points)
        n = len(points)
        splines = [0]*(n-1)
        threads = [None]*(n-1)
        for i in range(n-1):
            threads[i] = Thread(target=self.aux_compute, args=(i, points, splines))
            threads[i].start()
        for i in range(len(threads)):
            threads[i].join()

        function = []
        for i in range(n-1):
            function.append([splines[i], "{x0} <= x <= {x1}".format(
                x0=points[i][0], x1=points[i+1][0])])

        return function

    def computeSegment(self, point0, point1):
        segment = "{fx1} + ({fx1} - {fx0})/({x1} - {x0})*(x - {x1})".format(
            fx1=point1[1],
            fx0=point0[1],
            x1=point1[0],
            x0=point0[0])

        return str(sympify(segment))

points = {"X":[0,1,2,3],"Y":[0,1,1,0]}
x = LinearSpline()
print(x.compute(points))