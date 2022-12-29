package com.example.suprememath.ui.square_equation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aghajari.graphview.AXGraphFormula
import com.aghajari.graphview.AXGraphOptions
import com.example.suprememath.R
import com.example.suprememath.databinding.FragmentSquareBinding
import kotlin.math.sqrt


class SquareFragment : Fragment() {

    private lateinit var binding: FragmentSquareBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSquareBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("text",viewLifecycleOwner){requestKey,bundle ->
            val text = bundle.getString("text")

            binding.textView?.text = text
        }

        binding.solvex2.setOnClickListener {
                discriminant()
        }
        binding.ClearBtn.setOnClickListener {
            ClearBtn()
        }

        val graphView = binding.graphView
        val options = AXGraphOptions(requireContext())

        options.scrollEnabled = true
        options.xDividerIntervalInPx = 40F
        options.xDividerInterval = 0.5f
        options.yDividerIntervalInPx = 40F
        options.yDividerInterval = 0.5f
        options.maxZoom = 6f
        options.minZoom = 0.95f
        options.textPaint.color = Color.BLUE;
        options.gridLinePaint.color = Color.GRAY

        graphView.graphOptions = options;

       if (true){
           PlotXSquare()
       }
    }



    private fun PlotXSquare() {

        parentFragmentManager.setFragmentResultListener("xs",
            viewLifecycleOwner) { requestKey, bundle ->
            val x1 = bundle.getFloat("x1")
            val x2 = bundle.getFloat("x2")

            val xsquare = bundle.getFloat("xsquare")
            val c = bundle.getFloat("c23")
            val firstX = bundle.getFloat("firstx")

            val graphView = binding.graphView
            val options = AXGraphOptions(requireContext())

            options.scrollEnabled = true
            options.xDividerIntervalInPx = 40F
            options.xDividerInterval = 0.5f
            options.yDividerIntervalInPx = 40F
            options.yDividerInterval = 0.5f
            options.maxZoom = 6f
            options.minZoom = 0.75f
            options.textPaint.color = Color.BLUE;
            options.gridLinePaint.color = Color.GRAY

            graphView.graphOptions = options


            if (binding.X3?.text!!.isEmpty()){
                graphView.addFormula(object : AXGraphFormula() {
                    // x^2
                    override fun function(x: Float): Float {
                        return ((xsquare * Math.pow(x.toDouble(), 2.0)) + (firstX * x) + c).toFloat()
                    }

                    override fun init() {
                        super.init()
                        addCustomPoint(x1, 0f)
                        addCustomPoint(x2, 0f)

                    }
                })
            }


            if (binding.X3?.text!!.isNotEmpty()){
                val xCube = binding.X3?.text.toString().toFloat()

                graphView.addFormula(object : AXGraphFormula() {
                    // x^3
                    override fun function(x: Float): Float {
                        return ((xCube*Math.pow(x.toDouble(),3.0)+xsquare * Math.pow(x.toDouble(), 2.0)) + (firstX * x) + c).toFloat()
                    }

                })
            }


        }
    }

    private fun ClearBtn() {
        binding.X2.setText("")
        binding.firstX.setText("")
        binding.C.setText("")
        binding.digit1.setText("")


        binding.Discriminant.setText("D=?")
        binding.ansX.setText("x1=?")
        binding.ansX2.setText("x2=?")

        val graphView = binding.graphView
        val options = AXGraphOptions(requireContext())

        options.scrollEnabled = true
        options.xDividerIntervalInPx = 40F
        options.xDividerInterval = 0.5f
        options.yDividerIntervalInPx = 40F
        options.yDividerInterval = 0.5f
        options.maxZoom = 6f
        options.minZoom = 0.75f
        options.textPaint.setColor(Color.BLUE);
        options.gridLinePaint.setColor(Color.GRAY)

        graphView.graphOptions = options
        graphView.clearFormulas()

    }

    private fun discriminant() {
        if (binding.firstX.text.toString().equals("")||
            binding.X2.text.toString().equals("")||
            binding.C.text.toString().equals("")||
            binding.digit1.text.toString().equals("")
        ) {
            Toast.makeText(requireContext(),"Заполните пустые поля", Toast.LENGTH_SHORT).show()
        } else if (binding.firstX.text.toString().equals(".")||
            binding.X2.text.toString().equals(".")||
            binding.C.text.toString().equals(".")||
            binding.digit1.text.toString().equals(".")
        ) {
            Toast.makeText(requireContext(),".", Toast.LENGTH_SHORT).show()
        } else if (binding.firstX.text.toString().equals("-")||
            binding.X2.text.toString().equals("-")||
            binding.C.text.toString().equals("-")||
            binding.digit1.text.toString().equals("-")){
            Toast.makeText(requireContext(),"-", Toast.LENGTH_SHORT).show()
        }
        else{

            val a = binding.X2.text.toString().toFloat()
            val b = binding.firstX.text.toString().toFloat()
            val c1 = binding.C.text.toString().toFloat()
            val digit1 = binding.digit1.text.toString().toFloat()

            val c = c1+(-digit1)
            val c23 = (c1+(-digit1)).toString()

            var discriminant = ((b*b)-4*a*c).toDouble()
            if (binding.X3?.text!!.isEmpty()){
                binding.Discriminant.setText("D=$discriminant")
            }


            if (discriminant > 0){
                val x1 = (((-b)+ sqrt(discriminant))/(2*a)).toString()

                val x2 = (((-b)- sqrt(discriminant))/(2*a)).toString()
                if (x1.length>4||x2.length>4 && binding.X3?.text!!.isEmpty()){
                    binding.ansX2.setText("x2 = ${-b}-√${discriminant}/${2*a}")
                    binding.ansX.setText("x1 = ${-b}+√${discriminant}/${2*a}")
                }else{
                    binding.ansX2.setText("x2 = ${x2}")
                    binding.ansX.setText("x1 = ${x1}")
                }

                val xsquare = binding.X2.text.toString()
                val xfirst = binding.firstX.text.toString()


                val bundle = Bundle()
                bundle.putFloat("x1",x1.toFloat())
                bundle.putFloat("x2",x2.toFloat())
                bundle.putFloat("xsquare",xsquare.toFloat())
                bundle.putFloat("c23",c23.toFloat())
                bundle.putFloat("firstx",xfirst.toFloat())
                parentFragmentManager.setFragmentResult("xs",bundle)


                val bundleAlert = Bundle()
                val nominator = "${-b}±√${discriminant}"
                val denominator = "2*${a}"
                val x1_al = "x1 = ${x1}"
                val x2_al = "x2 = ${x2}"
                bundleAlert.putString("x1alert",x1_al)
                bundleAlert.putString("x2alert",x2_al)
                bundleAlert.putString("nominator",nominator)
                bundleAlert.putString("denominator",denominator)

                if (binding.X3?.text!!.isEmpty()){
                    val alertBinding = layoutInflater.inflate(R.layout.layout_x2_alert_dialog,null)

                    val alertDialog = Dialog(requireContext())
                    alertDialog.setContentView(alertBinding)
                    alertDialog.setCancelable(true)
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    val ans1 = alertDialog.findViewById<TextView>(R.id.ans1)
                    val ans2 = alertDialog.findViewById<TextView>(R.id.ans2)
                    val nominator_al = alertDialog.findViewById<TextView>(R.id.nominator)
                    val denominator_al = alertDialog.findViewById<TextView>(R.id.denominator)
                    ans1.text = bundleAlert.getString("x1alert")
                    ans2.text = bundleAlert.getString("x2alert")
                    nominator_al.text = bundleAlert.getString("nominator")
                    denominator_al.text = bundleAlert.getString("denominator")
                    alertDialog.show()
                }



            }
            if (discriminant.equals(0.0)){
                val x1 = (((-b)+ sqrt(0.0))/(2*a)).toString()

                val x2 = (((-b)- sqrt(discriminant))/(2*a)).toString()
                if (x1.length>4||x2.length>4 && binding.X3?.text!!.isEmpty()){
                    binding.ansX2.setText("x2 = ${-b}-√${0}/${2*a}")
                    binding.ansX.setText("x1 = ${-b}+√${0}/${2*a}")
                }else{
                    binding.ansX2.setText("x2 = ${x2}")
                    binding.ansX.setText("x1 = ${x1}")
                }

                val xsquare = binding.X2.text.toString()
                val xfirst = binding.firstX.text.toString()


                val bundle = Bundle()
                bundle.putFloat("x1",x1.toFloat())
                bundle.putFloat("x2",x2.toFloat())
                bundle.putFloat("xsquare",xsquare.toFloat())
                bundle.putFloat("c23",c23.toFloat())
                bundle.putFloat("firstx",xfirst.toFloat())
                parentFragmentManager.setFragmentResult("xs",bundle)

                val bundleAlert = Bundle()
                val nominator = "${-b}±√${0}"
                val denominator = "2*${a}"
                val x1_al = "x1 = ${x1}"
                val x2_al = "x2 = ${x2}"
                bundleAlert.putString("x1alert",x1_al)
                bundleAlert.putString("x2alert",x2_al)
                bundleAlert.putString("nominator",nominator)
                bundleAlert.putString("denominator",denominator)

                if (binding.X3?.text!!.isEmpty()){
                    val alertBinding = layoutInflater.inflate(R.layout.layout_x2_alert_dialog,null)

                    val alertDialog = Dialog(requireContext())
                    alertDialog.setContentView(alertBinding)
                    alertDialog.setCancelable(true)
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    val ans1 = alertDialog.findViewById<TextView>(R.id.ans1)
                    val ans2 = alertDialog.findViewById<TextView>(R.id.ans2)
                    val nominator_al = alertDialog.findViewById<TextView>(R.id.nominator)
                    val denominator_al = alertDialog.findViewById<TextView>(R.id.denominator)
                    ans1.text = bundleAlert.getString("x1alert")
                    ans2.text = bundleAlert.getString("x2alert")
                    nominator_al.text = bundleAlert.getString("nominator")
                    denominator_al.text = bundleAlert.getString("denominator")
                    alertDialog.show()
                }

            }
            if(discriminant < 0){
                val x1 = (((-b)+ sqrt(discriminant))/(2*a)).toString()

                val x2 = (((-b)- sqrt(discriminant))/(2*a)).toString()
                if (binding.X3?.text!!.isEmpty()){
                    binding.ansX.setText("x1 = $x1")
                    binding.ansX2.setText("x2 = $x2")
                }

                val xsquare = binding.X2.text.toString()
                val xfirst = binding.firstX.text.toString()

                val bundle = Bundle()
                bundle.putFloat("x1",x1.toFloat())
                bundle.putFloat("x2",x2.toFloat())
                bundle.putFloat("xsquare",xsquare.toFloat())
                bundle.putFloat("c23",c23.toFloat())
                bundle.putFloat("firstx",xfirst.toFloat())
                parentFragmentManager.setFragmentResult("xs",bundle)

                if (binding.X3?.text!!.isEmpty()){
                    binding.ansX.setText("x1 = ${-b} + √${-discriminant} * i/${2*a}")
                    binding.ansX2.setText("x2 = ${-b} - √${-discriminant} * i/${2*a}")
                }

                    val x1_al = "x1 = ${-b} + √${-discriminant} * i/${2*a}"
                    val x2_al = "x2 = ${-b} - √${-discriminant} * i/${2*a}"
                    val nominator = "${-b}-√${b}²-4*${a}*${c}"
                    val denominator = "2*${a}"

                if (binding.X3?.text!!.isEmpty()){
                    val bundleAlert = Bundle()
                    bundleAlert.putString("x1alert",x1_al)
                    bundleAlert.putString("x2alert",x2_al)
                    bundleAlert.putString("nominator",nominator)
                    bundleAlert.putString("denominator",denominator)

                    val alertBinding = layoutInflater.inflate(R.layout.layout_x2_alert_dialog,null)

                    val alertDialog = Dialog(requireContext())
                    alertDialog.setContentView(alertBinding)
                    alertDialog.setCancelable(true)
                    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    val ans1 = alertDialog.findViewById<TextView>(R.id.ans1)
                    val ans2 = alertDialog.findViewById<TextView>(R.id.ans2)
                    val nominator_al = alertDialog.findViewById<TextView>(R.id.nominator)
                    val denominator_al = alertDialog.findViewById<TextView>(R.id.denominator)
                    ans1.text = bundleAlert.getString("x1alert")
                    ans2.text = bundleAlert.getString("x2alert")
                    nominator_al.text = bundleAlert.getString("nominator")
                    denominator_al.text = bundleAlert.getString("denominator")
                    alertDialog.show()
                }
            }

        }
    }
}
