internal object GFG {
    var N = 4 // Number of unknowns

    var ansx = 0.0
    var ansy = 0.0
    var ansz = 0.0
    var ansk = 0.0

    // function to get matrix content
    fun gaussianElimination(mat: Array<DoubleArray>) : String {

        /* reduction into r.e.f. */
        val singular_flag = forwardElim(mat)

        /* if matrix is singular */if (singular_flag != -1) {
            println("Singular Matrix.")

            /* if the RHS of equation corresponding to
               zero row  is 0, * system has infinitely
               many solutions, else inconsistent*/if (mat[singular_flag][N] != 0.0) print("Inconsistent System.") else print(
                "May have infinitely many solutions."
            )
            return backSub(mat).toString()
        }

        /* get solution to system and print it using
           backward substitution */return backSub(mat).toString()
    }

    // function for elementary operation of swapping two
    // rows
    fun swap_row(mat: Array<DoubleArray>, i: Int, j: Int) {
        // printf("Swapped rows %d and %d\n", i, j);
        for (k in 0..N) {
            val temp = mat[i][k]
            mat[i][k] = mat[j][k]
            mat[j][k] = temp
        }
    }

    // function to print matrix content at any stage
    fun print(mat: Array<DoubleArray>) {
        var i = 0
        while (i < N) {
            for (j in 0..N) print(mat[i][j])
            i++
            println()
        }
        println()
    }

    // function to reduce matrix to r.e.f.
    fun forwardElim(mat: Array<DoubleArray>): Int {
        for (k in 0 until N) {

            // Initialize maximum value and index for pivot
            var i_max = k
            var v_max = mat[i_max][k].toInt()

            /* find greater amplitude for pivot if any */for (i in k + 1 until N) if (Math.abs(mat[i][k]) > v_max) {
                v_max = mat[i][k].toInt()
                i_max = i
            }

            /* if a principal diagonal element  is zero,
             * it denotes that matrix is singular, and
             * will lead to a division-by-zero later. */if (mat[k][i_max] == 0.0) return k // Matrix is singular

            /* Swap the greatest value row with current row
             */if (i_max != k) swap_row(mat, k, i_max)
            for (i in k + 1 until N) {

                /* factor f to set current row kth element
                 * to 0, and subsequently remaining kth
                 * column to 0 */
                val f = mat[i][k] / mat[k][k]

                /* subtract fth multiple of corresponding
                   kth row element*/for (j in k + 1..N) mat[i][j] -= mat[k][j] * f

                /* filling lower triangular matrix with
                 * zeros*/mat[i][k] = 0.0
            }
        }
        return -1
    }

    // function to calculate the values of the unknowns
    fun backSub(mat: Array<DoubleArray>) {
        val x = DoubleArray(N) // An array to store solution

        /* Start calculating from last equation up to the
           first */for (i in N - 1 downTo 0) {

            /* start with the RHS of the equation */
            x[i] = mat[i][N]

            /* Initialize j to i+1 since matrix is upper
               triangular*/for (j in i + 1 until N) {

                /* subtract all the lhs values
                 * except the coefficient of the variable
                 * whose value is being calculated */
                x[i] -= mat[i][j] * x[j]
            }

            /* divide the RHS by the coefficient of the
               unknown being calculated */x[i] = x[i] / mat[i][i]
        }
        println()
        println("Solution for the system:")
        for (i in 0 until N) {
            System.out.format("%.4f", x[i])
            ansx = x[0]
            ansy = x[1]
            ansz = x[2]
            ansk = x[3]
            println()
        }
        println("_________")
        println(ansx)
        println(ansy)
        println(ansz)
        println(ansk)
    }

    // Driver program
    @JvmStatic
    fun main(args: Array<String>) {

        /* input matrix */
        val mat = arrayOf(
            doubleArrayOf(6.1,6.2,-6.3,6.4,6.5),
            doubleArrayOf(1.1,-1.5,2.2,-3.8,4.2),
            doubleArrayOf(5.1,-5.0,4.9,-4.8,4.7),
            doubleArrayOf(1.8,1.9,2.0,-2.1,2.2),
        )
        gaussianElimination(mat).toString()
    }
}