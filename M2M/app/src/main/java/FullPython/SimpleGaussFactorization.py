import numpy as np

class SimpleGauss:

    def __init__(self):
        self.n = 4

    def SimpleGauss(self,A,b):
        L=[]
        L=self.Fill()
        U=A
        var=np.linalg.det(A)
        if var!=0:
            mult=0
            for k in range(0,self.n):
                for i in range(k+1,self.n):
                    mult=U[i][k]/U[k][k]
                    L[i][k]=mult
                    for j in range(k,self.n):
                        U[i][j] = U[i][j] - mult*U[k][j]
            print ("U Matrix : ",U)
            print ("L Matrix : ",L)
            z= self.ProgressiveSubstitution(L,b)
            print("Z values : ",z)
            x= self.RegressiveSubstitution(U,z)
            return x
        else:
            return("Error: Can't solve the system")
    

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
    
    def Fill(self):
        L=[]
        LAux=[]
        for f in range(0,self.n):
            for g in range(0,self.n):
                LAux.append(0)
            L.append(LAux)
            LAux=[]
        for h in range(0,self.n):
            L[h][h]=1
        return L
    
    def main(self):
        A=[[3,-2,-2,-1],[-2,8,-2,-2],[-2,-2,4,2],[-1,-2,2,3]]
        b=[1,1,1,1]
        print(self.SimpleGauss(A,b))

sg = SimpleGauss()
sg.main()


