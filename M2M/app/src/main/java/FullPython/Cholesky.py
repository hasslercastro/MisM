class Cholesky:

    def __init__(self):
        self.n = 4

    def Cholesky(self, A):

        L = self.ZeroMatrix(1)
        U = self.ZeroMatrix(1)

        for k in range(self.n):
            sum = 0
            for p in range(0, k):
                sum = sum+(L[k][p]*U[p][k])
            L[k][k] = (A[k][k]-sum)**(1/2)
            U[k][k] = L[k][k]
            for i in range(k+1, self.n, 1):
                sum = 0
                for p in range(k):
                    sum = sum+L[i][p]*U[p][k]
                L[i][k] = (A[i][k]-sum)/U[k][k]
            for j in range(k+1, self.n):
                sum = 0
                for p in range(k):
                    sum = sum+L[k][p]*U[p][j]
                U[k][j] = (A[k][j]-sum)/L[k][k]
        return L, U

    def main(self):
        A = [[3, -2, -2, -1], [-2, 8, -2, -2], [-2, -2, 4, 2], [-1, -2, 2, 3]]
        b = [1, 1, 1, 1]
        L_U = self.Cholesky(A)
        print("L Matrix : ", L_U[0])
        print("U Matrix : ", L_U[1])
        z = self.ProgressiveSubstitution(L_U[0], b)
        x = self.RegressiveSubstitution(L_U[1], z)
        print("Z values : ", z)
        print("X values : ", x)

    def RegressiveSubstitution(self, U, z):
        Uz = self.AugmentedMatrix(U, z)
        x = []
        for j in range(0, self.n-1):
            x.append(1)
        x.append(Uz[self.n-1][self.n] /
                 Uz[self.n-1][self.n-1])
        for i in range(self.n-1, -1, -1):
            accum = 0
            for p in range(i+1, self.n):
                accum += Uz[i][p]*x[p]
            x[i] = ((Uz[i][self.n]-accum)/Uz[i][i])
        return x

    def AugmentedMatrix(self, A, b):
        i = 0
        Ab = []
        while i < len(A):
            A[i].append(b[i])
            i += 1
        return A

    def ProgressiveSubstitution(self, L, b):
        Lb = self.AugmentedMatrix(L, b)
        x = []
        x.append(Lb[0][self.n]/Lb[0][0])
        for j in range(0, self.n-1):
            x.append(1)
        for i in range(1, self.n):
            accum = 0
            for p in range(0, i):
                accum += Lb[i][p]*x[p]
            x[i] = ((Lb[i][self.n]-accum)/Lb[i][i])
        return x

    def ZeroMatrix(self, valor):
        L = []
        LAux = []
        for f in range(0, self.n):
            for g in range(0, self.n):
                if(g == f):
                    LAux.append(valor)
                else:
                    LAux.append(0)
            L.append(LAux)
            LAux = []
        return L


clk = Cholesky()
clk.main()
