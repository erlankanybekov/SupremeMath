package com.example.suprememath.ui.matrix

import GFG
import GFG.gaussianElimination
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.suprememath.R
import com.example.suprememath.databinding.FragmentMatrixBinding
import okio.ByteString.Companion.encode

class MatrixFragment : Fragment() {

    private lateinit var binding: FragmentMatrixBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatrixBinding.inflate(inflater, container, false)
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
//            BtnClear()
        }
        binding.dropMenu.onItemClickListener =
            AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
                val item = p0?.getItemAtPosition(p2).toString()

                when (item) {

                    "solve matrix" -> {
                        try {
                            Gaussian()
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
//                        Showallxyz()
                    }
                    "solve x,y system" -> {
//                        Kramer()
//                        hideZ()
                    }
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun Gaussian() {

        /* input matrix */
        with(binding){

            val inputs = listOf(x1?.text.toString(),y1?.text.toString(),z1?.text.toString(),k1?.text.toString(),dig1?.text.toString(),
                x2?.text.toString(),y2?.text.toString(),z2?.text.toString(),k2?.text.toString(),dig2?.text.toString(),
                x3?.text.toString(),y3?.text.toString(),z3?.text.toString(),k3?.text.toString(),dig3?.text.toString(),
                x4?.text.toString(),y4?.text.toString(),z4?.text.toString(),k4?.text.toString(),dig4?.text.toString())
            if (inputs.contains("")){
                Toast.makeText(requireContext(), "заполните все поля", Toast.LENGTH_SHORT).show()
            }

            val mat = arrayOf(
                doubleArrayOf(x1?.text.toString().toDouble(),y1?.text.toString().toDouble(),z1?.text.toString().toDouble(),k1?.text.toString().toDouble(),dig1?.text.toString().toDouble()),
                doubleArrayOf(x2?.text.toString().toDouble(),y2?.text.toString().toDouble(),z2?.text.toString().toDouble(),k2?.text.toString().toDouble(),dig2?.text.toString().toDouble()),
                doubleArrayOf(x3?.text.toString().toDouble(),y3?.text.toString().toDouble(),z3?.text.toString().toDouble(),k3?.text.toString().toDouble(),dig3?.text.toString().toDouble()),
                doubleArrayOf(x4?.text.toString().toDouble(),y4?.text.toString().toDouble(),z4?.text.toString().toDouble(),k4?.text.toString().toDouble(),dig4?.text.toString().toDouble())
            )
           gaussianElimination(mat)
            ansX.text = "x = ${GFG.ansx}\n" +
                    "y = ${GFG.ansy}\n" +
                    "z = ${GFG.ansz}\n" +
                    "k = ${GFG.ansk}\n"
        }
    }

}