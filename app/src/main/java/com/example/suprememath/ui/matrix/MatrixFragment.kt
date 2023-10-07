package com.example.suprememath.ui.matrix

import com.example.suprememath.ui.matrix.GFG4D.gaussianElimination
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.suprememath.R
import com.example.suprememath.databinding.FragmentMatrixBinding
import com.example.suprememath.ui.matrix.GFG2D.gaussianElimination2D
import com.example.suprememath.ui.matrix.GFG3D.gaussianElimination3D

class MatrixFragment : Fragment() {

    private val binding: FragmentMatrixBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentMatrixBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.dropMenu.inputType = 0
        val solves = resources.getStringArray(R.array.sovles)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, solves)
        binding.dropMenu.setAdapter(adapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ClearBtn.setOnClickListener {
            BtnClear()
        }
        binding.dropMenu.onItemClickListener =
            AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
                val item = p0?.getItemAtPosition(p2).toString()

                when (item) {
                    "solve matrix" -> {
                        try {
                            Gaussian4D()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    "solve x,y,z system" -> {
                        try {
                            Gaussian3D()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    "solve x,y system" -> {
                        try {
                            Gaussian2D()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun Gaussian2D() {
        with(binding) {
            nullcheck2D()
            val mat = arrayOf(
                doubleArrayOf(
                    x1?.text.toString().toDouble(),
                    y1?.text.toString().toDouble(),
                    dig1?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x2?.text.toString().toDouble(),
                    y2?.text.toString().toDouble(),
                    dig2?.text.toString().toDouble()
                )
            )
            gaussianElimination2D(mat)
            ansX.text = "x = ${GFG2D.ansx}\n" +
                    "y = ${GFG2D.ansy}\n"

        }
    }

    @SuppressLint("SetTextI18n")
    private fun Gaussian3D() {
        /* input matrix */
        with(binding) {
            nullcheck3D()
            val mat = arrayOf(
                doubleArrayOf(
                    x1?.text.toString().toDouble(),
                    y1?.text.toString().toDouble(),
                    z1?.text.toString().toDouble(),
                    dig1?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x2?.text.toString().toDouble(),
                    y2?.text.toString().toDouble(),
                    z2?.text.toString().toDouble(),
                    dig2?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x3?.text.toString().toDouble(),
                    y3?.text.toString().toDouble(),
                    z3?.text.toString().toDouble(),
                    dig3?.text.toString().toDouble()
                ),
            )
            gaussianElimination3D(mat)
            ansX.text = "x = ${GFG3D.ansx}\n" +
                    "y = ${GFG3D.ansy}\n" +
                    "z = ${GFG3D.ansz}\n"
        }
    }

    private fun FragmentMatrixBinding.nullcheck3D() {
        val inputs = listOf(
            x1?.text.toString(), y1?.text.toString(), z1?.text.toString(), dig1?.text.toString(),
            x2?.text.toString(), y2?.text.toString(), z2?.text.toString(), dig2?.text.toString(),
            x3?.text.toString(), y3?.text.toString(), z3?.text.toString(), dig3?.text.toString(),
        )

        if (inputs.contains("")) {
            Toast.makeText(requireContext(), "заполните все x,y,z поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun BtnClear() {
        with(binding) {
            x1?.setText("")
            x2?.setText("")
            x3?.setText("")
            x4?.setText("")

            y1?.setText("")
            y2?.setText("")
            y3?.setText("")
            y4?.setText("")

            z1?.setText("")
            z2?.setText("")
            z3?.setText("")
            z4?.setText("")

            k1?.setText("")
            k2?.setText("")
            k3?.setText("")
            k4?.setText("")

            dig1?.setText("")
            dig2?.setText("")
            dig3?.setText("")
            dig4?.setText("")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun Gaussian4D() {
        /* input matrix */
        with(binding) {
            nullcheck4D()
            val mat = arrayOf(
                doubleArrayOf(
                    x1?.text.toString().toDouble(),
                    y1?.text.toString().toDouble(),
                    z1?.text.toString().toDouble(),
                    k1?.text.toString().toDouble(),
                    dig1?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x2?.text.toString().toDouble(),
                    y2?.text.toString().toDouble(),
                    z2?.text.toString().toDouble(),
                    k2?.text.toString().toDouble(),
                    dig2?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x3?.text.toString().toDouble(),
                    y3?.text.toString().toDouble(),
                    z3?.text.toString().toDouble(),
                    k3?.text.toString().toDouble(),
                    dig3?.text.toString().toDouble()
                ),
                doubleArrayOf(
                    x4?.text.toString().toDouble(),
                    y4?.text.toString().toDouble(),
                    z4?.text.toString().toDouble(),
                    k4?.text.toString().toDouble(),
                    dig4?.text.toString().toDouble()
                )
            )
            gaussianElimination(mat)
            ansX.text = "x = ${GFG4D.ansx}\n" +
                    "y = ${GFG4D.ansy}\n" +
                    "z = ${GFG4D.ansz}\n" +
                    "k = ${GFG4D.ansk}\n"
        }
    }

    private fun FragmentMatrixBinding.nullcheck2D() {
        val inputs = listOf(
            x1?.text.toString(),
            y1?.text.toString(),
            dig1?.text.toString(),

            x2?.text.toString(),
            y2?.text.toString(),
            dig2?.text.toString()
        )
        if (inputs.contains("")) {
            Toast.makeText(requireContext(), "заполните x,y поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun FragmentMatrixBinding.nullcheck4D() {
        val inputs = listOf(
            x1?.text.toString(),
            y1?.text.toString(),
            z1?.text.toString(),
            k1?.text.toString(),
            dig1?.text.toString(),
            x2?.text.toString(),
            y2?.text.toString(),
            z2?.text.toString(),
            k2?.text.toString(),
            dig2?.text.toString(),
            x3?.text.toString(),
            y3?.text.toString(),
            z3?.text.toString(),
            k3?.text.toString(),
            dig3?.text.toString(),
            x4?.text.toString(),
            y4?.text.toString(),
            z4?.text.toString(),
            k4?.text.toString(),
            dig4?.text.toString()
        )
        if (inputs.contains("")) {
            Toast.makeText(requireContext(), "заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}