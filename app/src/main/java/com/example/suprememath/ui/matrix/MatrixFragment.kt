package com.example.suprememath.ui.matrix

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

class MatrixFragment : Fragment() {

    private lateinit var binding: FragmentMatrixBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatrixBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.dropMenu.inputType = 0
        val solves = resources.getStringArray(R.array.sovles)
        val adapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,solves)
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

                when(item){

                    "solve matrix"->{
                        methodKramer()
                        Showallxyz()

                    }
                    "solve x,y system"->{
                        Kramer()
                        hideZ()
                    }
                }
            }
    }



    private fun hideZ() {
        binding.txtX3.visibility = View.INVISIBLE
        binding.txtY3.visibility = View.INVISIBLE
        binding.txtZ.visibility = View.INVISIBLE
        binding.txtZ2.visibility = View.INVISIBLE
        binding.txtZ3.visibility = View.INVISIBLE

        binding.firstZ.visibility = View.INVISIBLE
        binding.secondZ.visibility = View.INVISIBLE
        binding.digit3.visibility = View.INVISIBLE

        binding.thirdX.visibility = View.INVISIBLE
        binding.thirdY.visibility = View.INVISIBLE
        binding.thirdZ.visibility = View.INVISIBLE

        binding.txtplus3.visibility = View.INVISIBLE
        binding.txtequal5.visibility = View.INVISIBLE
        binding.txtequal6.visibility = View.INVISIBLE
        binding.txtequal.visibility = View.INVISIBLE
        binding.txtequal2.visibility = View.INVISIBLE
        binding.txtZ3.visibility = View.INVISIBLE
        binding.txtZ2.visibility = View.INVISIBLE

        binding.ansZ.visibility = View.GONE

        binding.firstX.visibility = View.VISIBLE
        binding.firstY.visibility = View.VISIBLE
        binding.txtX.visibility = View.VISIBLE
        binding.txtY.visibility = View.VISIBLE
        binding.txtplus.visibility = View.VISIBLE
        binding.txtequal3.visibility = View.VISIBLE
        binding.digit1.visibility = View.VISIBLE
    }

    private fun Showallxyz() {
        binding.txtX3.visibility = View.VISIBLE
        binding.txtY3.visibility = View.VISIBLE
        binding.txtZ.visibility = View.VISIBLE
        binding.txtZ2.visibility = View.VISIBLE
        binding.txtZ3.visibility = View.VISIBLE

        binding.firstZ.visibility = View.VISIBLE
        binding.secondZ.visibility = View.VISIBLE
        binding.digit3.visibility = View.VISIBLE

        binding.thirdX.visibility = View.VISIBLE
        binding.thirdY.visibility = View.VISIBLE
        binding.thirdZ.visibility = View.VISIBLE

        binding.txtplus3.visibility = View.VISIBLE
        binding.txtequal5.visibility = View.VISIBLE
        binding.txtequal6.visibility = View.VISIBLE
        binding.txtequal.visibility = View.VISIBLE
        binding.txtequal2.visibility = View.VISIBLE
        binding.txtZ3.visibility = View.VISIBLE
        binding.txtZ2.visibility = View.VISIBLE


        binding.firstX.visibility = View.VISIBLE
        binding.firstY.visibility = View.VISIBLE
        binding.firstZ.visibility = View.VISIBLE
        binding.txtX.visibility = View.VISIBLE
        binding.txtY.visibility = View.VISIBLE
        binding.txtY.visibility = View.VISIBLE
        binding.txtplus.visibility = View.VISIBLE
        binding.txtequal3.visibility = View.VISIBLE
        binding.digit1.visibility = View.VISIBLE


        binding.ansZ.visibility = View.VISIBLE
    }


    private fun Kramer() {



        if (binding.firstX.text.toString().equals("")||
            binding.firstY.text.toString().equals("") ||

            binding.secondX.text.toString().equals("")||
            binding.secondY.text.toString().equals("") ||


            binding.digit1.text.toString().equals("") ||
            binding.digit2.text.toString().equals("")
        )
        {
            Toast.makeText(requireContext(),"Заполните поля 1 и 2 строки",Toast.LENGTH_SHORT).show()
        } else if (binding.firstX.text.toString().equals(".")||
            binding.firstY.text.toString().equals(".") ||

            binding.secondX.text.toString().equals(".")||
            binding.secondY.text.toString().equals(".") ||


            binding.digit1.text.toString().equals(".") ||
            binding.digit2.text.toString().equals(".")
        )
        {
            Toast.makeText(requireContext(),".",Toast.LENGTH_SHORT).show()
        } else if (binding.firstX.text.toString().equals("-")||
            binding.firstY.text.toString().equals("-") ||

            binding.secondX.text.toString().equals("-")||
            binding.secondY.text.toString().equals("-") ||


            binding.digit1.text.toString().equals("-") ||
            binding.digit2.text.toString().equals("-")){
            Toast.makeText(requireContext(),"-",Toast.LENGTH_SHORT).show()
        }
        else{
            val x1 = binding.firstX.text.toString().toDouble()
            val x2 = binding.secondX.text.toString().toDouble()

            val y1 = binding.firstY.text.toString().toDouble()
            val y2 = binding.secondY.text.toString().toDouble()

            val digit1 = binding.digit1.text.toString().toDouble()
            val digit2 = binding.digit2.text.toString().toDouble()

            //determinant
            val determinant = (x1 * y2) - (y1 * x2)

            val ansX = ((digit1 * y2 - y1 * digit2) / determinant).toString()
            val ansY = ((x1 * digit2 - digit1 * x2)/ determinant).toString()


            val chislX = (digit1 * y2 - y1 * digit2)
            val chislY = (x1 * digit2 - digit1 * x2)

            //setText
            binding.ansX.setText("x =$chislX/${determinant}")
            binding.ansY.setText("y =$chislY/${determinant}")

            binding.ansZ.visibility = View.GONE

        }


    }

    private fun BtnClear() {
        binding.firstX.setText("")
        binding.secondX.setText("")
        binding.thirdX.setText("")
        binding.firstY.setText("")
        binding.secondY.setText("")
        binding.thirdY.setText("")
        binding.firstZ.setText("")
        binding.secondZ.setText("")
        binding.thirdZ.setText("")
        binding.digit1.setText("")
        binding.digit2.setText("")
        binding.digit3.setText("")

        binding.ansX.setText("x = ?")
        binding.ansY.setText("y = ?")
        binding.ansZ.setText("z = ?")

    }

    private fun methodKramer() {
        if (binding.firstX.text.toString().contains("*")|| binding.firstX.text.toString().contains("N")|| binding.firstX.text.toString().contains(",")|| binding.firstX.text.toString().contains("+")|| binding.firstX.text.toString().contains("#")|| binding.firstX.text.toString().contains(")")|| binding.firstX.text.toString().contains("/")|| binding.firstX.text.toString().contains("(")||
            binding.firstY.text.toString().contains("*")|| binding.firstY.text.toString().contains("N") || binding.firstY.text.toString().contains(",") || binding.firstY.text.toString().contains("+") || binding.firstY.text.toString().contains("#") || binding.firstY.text.toString().contains(")") || binding.firstY.text.toString().contains("/") || binding.firstY.text.toString().contains("(") ||
            binding.firstZ.text.toString().contains("*")||  binding.firstZ.text.toString().contains("N")||  binding.firstZ.text.toString().contains(",")||  binding.firstZ.text.toString().contains("+")|| binding.firstZ.text.toString().contains("#")||  binding.firstZ.text.toString().contains(")")||  binding.firstZ.text.toString().contains("/")||  binding.firstZ.text.toString().contains("(")||
            binding.secondX.text.toString().contains("*")||binding.secondX.text.toString().contains("N")||binding.secondX.text.toString().contains(",")||binding.secondX.text.toString().contains("+")||binding.secondX.text.toString().contains("#")||binding.secondX.text.toString().contains(")")||binding.secondX.text.toString().contains("/")||binding.secondX.text.toString().contains("(")||
            binding.secondY.text.toString().contains("*") || binding.secondY.text.toString().contains("N") ||binding.secondY.text.toString().contains(",") ||binding.secondY.text.toString().contains("+") ||binding.secondY.text.toString().contains("#") ||binding.secondY.text.toString().contains(")") ||binding.secondY.text.toString().contains("/") ||  binding.secondY.text.toString().contains("(") ||
            binding.secondZ.text.toString().contains("*") || binding.secondZ.text.toString().contains("N") ||  binding.secondZ.text.toString().contains(",") ||  binding.secondZ.text.toString().contains("+") ||  binding.secondZ.text.toString().contains("#") ||  binding.secondZ.text.toString().contains(")") ||  binding.secondZ.text.toString().contains("/") || binding.secondZ.text.toString().contains("(") ||
            binding.thirdX.text.toString().contains("*")|| binding.thirdX.text.toString().contains("N")||binding.thirdX.text.toString().contains(",")||binding.thirdX.text.toString().contains("+")||binding.thirdX.text.toString().contains("#")||binding.thirdX.text.toString().contains(")")||binding.thirdX.text.toString().contains("/")||binding.thirdX.text.toString().contains("(")||
            binding.thirdY.text.toString().contains("*")||  binding.thirdY.text.toString().contains("N")|| binding.thirdY.text.toString().contains(",")|| binding.thirdY.text.toString().contains("+")|| binding.thirdY.text.toString().contains("#")|| binding.thirdY.text.toString().contains(")")|| binding.thirdY.text.toString().contains("/")|| binding.thirdY.text.toString().contains("(")||
            binding.thirdZ.text.toString().contains("*")|| binding.thirdZ.text.toString().contains("N")||binding.thirdZ.text.toString().contains(",")||binding.thirdZ.text.toString().contains("+")||binding.thirdZ.text.toString().contains("#")||binding.thirdZ.text.toString().contains(")")||binding.thirdZ.text.toString().contains("/")|| binding.thirdZ.text.toString().contains("(")||
            binding.digit1.text.toString().contains("*") || binding.digit1.text.toString().contains("N") ||binding.digit1.text.toString().contains(",") ||binding.digit1.text.toString().contains("+") ||binding.digit1.text.toString().contains("#") ||binding.digit1.text.toString().contains(")") ||binding.digit1.text.toString().contains("/") || binding.digit1.text.toString().contains("(") ||
            binding.digit2.text.toString().contains("*") ||  binding.digit2.text.toString().contains("N") || binding.digit2.text.toString().contains(",") || binding.digit2.text.toString().contains("+") || binding.digit2.text.toString().contains("#") || binding.digit2.text.toString().contains(")") || binding.digit2.text.toString().contains("/") || binding.digit2.text.toString().contains("(") ||
            binding.digit3.text.toString().contains("*")|| binding.digit3.text.toString().contains("N")||binding.digit3.text.toString().contains(",")||binding.digit3.text.toString().contains("+")||binding.digit3.text.toString().contains("#")||binding.digit3.text.toString().contains(")")||binding.digit3.text.toString().contains("/")|| binding.digit3.text.toString().contains("(")){
            Toast.makeText(requireContext(),"вы ввели символы",Toast.LENGTH_SHORT).show()
        }


        if (binding.firstX.text.toString().equals("")||
            binding.firstY.text.toString().equals("") ||
            binding.firstZ.text.toString().equals("")||
            binding.secondX.text.toString().equals("")||
            binding.secondY.text.toString().equals("") ||
            binding.secondZ.text.toString().equals("") ||
            binding.thirdX.text.toString().equals("") ||
            binding.thirdY.text.toString().equals("") ||
            binding.thirdZ.text.toString().equals("") ||
            binding.digit1.text.toString().equals("") ||
            binding.digit2.text.toString().equals("")  ||
            binding.digit3.text.toString().equals("")
        )
        {
            Toast.makeText(requireContext(),"Заполните все поля!", Toast.LENGTH_SHORT).show()
        } else  if (binding.firstX.text.toString().equals(".")||
            binding.firstY.text.toString().equals(".") ||
            binding.firstZ.text.toString().equals(".")||
            binding.secondX.text.toString().equals(".")||
            binding.secondY.text.toString().equals(".") ||
            binding.secondZ.text.toString().equals(".") ||
            binding.thirdX.text.toString().equals(".") ||
            binding.thirdY.text.toString().equals(".") ||
            binding.thirdZ.text.toString().equals(".") ||
            binding.digit1.text.toString().equals(".") ||
            binding.digit2.text.toString().equals(".")  ||
            binding.digit3.text.toString().equals(".")
        )
        {
            Toast.makeText(requireContext(),".", Toast.LENGTH_SHORT).show()
        }
        else{
            val x1 = binding.firstX.text.toString().toDouble()
            val x2 = binding.secondX.text.toString().toDouble()
            val x3 = binding.thirdX.text.toString().toDouble()
            val y1 = binding.firstY.text.toString().toDouble()
            val y2 = binding.secondY.text.toString().toDouble()
            val y3 = binding.thirdY.text.toString().toDouble()
            val z1 = binding.firstZ.text.toString().toDouble()
            val z2 = binding.secondZ.text.toString().toDouble()
            val z3 = binding.thirdZ.text.toString().toDouble()

            val digit1 = binding.digit1.text.toString().trim().toDouble()
            val digit2 = binding.digit2.text.toString().trim().toDouble()
            val digit3 = binding.digit3.text.toString().trim().toDouble()

            //determinant
            val determinant1 = (x1 * y2 * z3) + (y1 *z2 * x3) + (z1 * y3 * x2).toString().toDouble()
            val determinant2 = (z1 * y2 * x3) + (y1* x2* z3) + (x1 * y3 * z2).toString().toDouble()

            val determinant = (determinant1 - determinant2).toString().toDouble()


            //ansX
            val ansX1 = (digit1 * y2 * z3) + (y1 *z2 * digit3) + (z1 * y3 * digit2).toString().toDouble()
            var ansX2 = (z1 * y2 * digit3) + (y1* digit2* z3) + (digit1 * y3 * z2).toString().toDouble()

            val ansX = ((ansX1 - ansX2) / determinant).toString()

            // ansY
            val ansY1 = (x1 * digit2 * z3) + (digit1 *z2 * x3) + (z1 * digit3 * x2).toString().toDouble()
            val ansY2 = (z1 * digit2 * x3) + (digit1* x2* z3) + (x1 * digit3 * z2).toString().toDouble()

            var ansY = ((ansY1-ansY2)/determinant).toString()

            // ansZ
            val ansZ1 = (x1 * y2 * digit3) + (y1 *digit2 * x3) + (digit1 * y3 * x2).toString().toDouble()
            val ansZ2 = (digit1 * y2 * x3) + (y1* x2* digit3) + (x1 * y3 * digit2).toString().toDouble()

            val ansZ = ((ansZ1 - ansZ2)/determinant).toString()
            binding.ansZ.visibility = View.VISIBLE

            val chislX = (ansX1 - ansX2)
            val chislY = (ansY1-ansY2)
            val chislZ = (ansZ1 - ansZ2)


            // setText ans
            binding.ansX.setText("x =$chislX/${determinant}")
            binding.ansY.setText("y =$chislY/${determinant}")
            binding.ansZ.setText("z =$chislZ/${determinant}")
        }






    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}