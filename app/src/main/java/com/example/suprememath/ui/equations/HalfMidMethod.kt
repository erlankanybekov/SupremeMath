package com.example.suprememath.ui.equations

internal object HalfMidMethod {
    interface HalfMidFunction {
        fun f(x: Double): Double
    }

    private fun isGoodEnough(left: Double, right: Double): Boolean {
        return right - left < 0.0001
    }

    private fun getAdgePoint(y: HalfMidFunction, middle: Double, x: Double): Double {
        val y1 = y.f(middle)
        val y2 = y.f(x)
        return if (y1 > 0 && y2 > 0) {
            middle
        } else if (y1 < 0 && y2 < 0) {
            middle
        } else {
            x
        }
    }

    private fun getMiddle(left: Double, right: Double): Double {
        return (left + right) / 2
    }

    private fun getRoot(y: HalfMidFunction, left: Double, middle: Double, right: Double): Double {
        //System.out.println("left=" + left + ", middle=" + middle + ", right=" + right);
        var left = left
        var middle = middle
        var right = right
        if (isGoodEnough(left, right)) {
            return getMiddle(left, right)
        }
        left = getAdgePoint(y, middle, left)
        right = getAdgePoint(y, middle, right)
        middle = getMiddle(left, right)
        return getRoot(y, left, middle, right)
    }

    fun getRoot(y: HalfMidFunction, left: Double, right: Double): Double {
        return getRoot(y, left, getMiddle(left, right), right)
    }

    @JvmStatic
    fun main(arvg: Array<String>) {
        val func: HalfMidFunction = object : HalfMidFunction {
            override fun f(x: Double): Double {
                return Math.pow(x, 3.0) + 3 * Math.pow(x, 2.0) - 3.5
            }
        }
        println(
            "x^3+3*x^2-3.5=0\n" +
                    "x between left and half " + getRoot(func, -2.0, -1.0)
        )
    }
}