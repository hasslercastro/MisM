import numpy as np

class TotalPivoting:

    def __init__(self):
        self.n = 3

    def TotalPivoting(self, A, b):
        var = np.linalg.det(A)
        if var != 0:
            Ab = []
            marks = []
            for initialList in range(1, self.n+1):
                marks.append(initialList)
            maxi = 0
            maxRow = 0
            mult = 0.0
            maxColumn = 0
            Ab = self.Augmented(A, b)
            for b in range(0, self.n-1):  # b=k
                maxi = 0
                maxRow = b
                maxColumn = b
                for r in range(b, self.n):
                    for s in range(b, self.n):
                        if abs(Ab[r][s]) > maxi:
                            maxi = abs(Ab[r][s])
                            maxRow = r
                            maxColumn = s
                if maxi == 0:
                    print("Error")
                    quit()
                elif maxRow != b:
                    Ab = self.rowSwap(Ab, maxRow, b)
                if maxColumn != b:
                    Ab = self.columnSwap(Ab, maxColumn, b)
                    marks = self.marksSwap(marks, maxColumn, b)
                for k in range(b, b+1):

                    for i in range(k+1, self.n):
                        mult = Ab[i][k]/Ab[k][k]
                        for j in range(k, self.n+1):
                            Ab[i][j] = Ab[i][j] - mult*Ab[k][j]
            return Ab, marks
        else:
            return("Can't solve the system")

    def Augmented(self, A, b):
        i = 0
        Ab = []
        while i < len(A):
            A[i].append(b[i])
            i += 1
        return A

    def marksSwap(self, marks, maxColumn, b):
        temp1 = marks[maxColumn]
        temp2 = marks[b]
        marks[maxColumn] = temp2
        marks[b] = temp1
        return marks

    def columnSwap(self, Ab, maxColumn, b):
        tempList = []
        tList = []
        for i in range(0, self.n):
            tempList.append(Ab[i][maxColumn])
            tList.append(Ab[i][b])
        for g in range(0, len(tList)):
            Ab[g][b] = tempList[g]
            Ab[g][maxColumn] = tList[g]
        return Ab

    def rowSwap(self, Ab, i, j):
        list = []
        tList = []
        list = Ab[i]
        tList = Ab[j]
        Ab[j] = list
        Ab[i] = tList
        return Ab

    def RegressiveSubstitution(self, A, b):
        solution = self.TotalPivoting(A, b)
        Ab = solution[0]
        marks = solution[1]
        x = []
        for j in range(0, self.n-1):
            x.append(1)
        x.append(Ab[self.n-1][self.n]/Ab[self.n-1][self.n-1])
        for i in range(self.n-1, -1, -1):
            accum = 0
            for p in range(i+1, self.n):
                accum += Ab[i][p]*x[p]
            x[i] = ((Ab[i][self.n]-accum)/Ab[i][i])
        return x, marks

    def main(self):
        A = [[0, 2, 3], [7, -1, 20], [1, -14, 8]]
        b = [4, -2, 8]
        print(self.RegressiveSubstitution(A, b))

tp = TotalPivoting()
tp.main()
