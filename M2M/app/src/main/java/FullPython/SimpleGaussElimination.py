def SimpleGauss(A, b):
    n = len(A)
    M = A

    i = 0
    for x in M:
        x.append(b[i])
        i += 1

    for k in range(n):
        print "Iteration ", k
        for i in range(k, n):
            if abs(M[i][k]) > abs(M[k][k]):
                M[k], M[i] = M[i], M[k]
            else:
                pass

        # Show the matrix after swapping rows
        for row in M:
            print row
        print

        for j in range(k+1, n):
            q = M[j][k] / M[k][k]
            for m in range(k, n+1):
                M[j][m] += q * M[k][m]

        # Show matrix after multiplying rows
        for row in M:
            print row
        print

    x = [0 for i in range(n)]

    print "n = ", n
    print "x = ", x
    for row in M:
        print row

    x[n-1] = float(M[n-1][n])/M[n-1][n-1]
    for i in range(n-1, -1, -1):
        z = 0
        for j in range(i+1, n):
            z = z + float(M[i][j])*x[j]
        x[i] = float(M[i][n] - z)/M[i][i]
    print x

A=[[3,41,-6],[65,76,-12],[34,6,2]]
b=[2,4,7]

SimpleGauss(A,b)
