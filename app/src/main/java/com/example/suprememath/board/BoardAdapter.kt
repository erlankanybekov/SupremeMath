package com.example.suprememath.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suprememath.R
import com.example.suprememath.databinding.PagerBoardBinding


class BoardAdapter(private val startClick: () -> Unit) :RecyclerView.Adapter<BoardAdapter.ViewHolder>() {


    private val titles = arrayListOf("Welcome to MathSupreme!","","Your personal Supreme Calculator!")
    private val description = arrayListOf("This application will help you in calculating complex arithmetic, trigonometric problems"
        ,"also it can help to solve problems related to the matrix or complex systems of equations and quadratic equations","","")

     inner class ViewHolder(private  var binding: PagerBoardBinding):RecyclerView.ViewHolder(binding.root) {
         fun bind(position: Int) {

             with(binding) {
                 when(position){
                     0->{lottieAnim.setAnimation(R.raw.teacherofmathematics)}
                     1->{lottieAnim.setAnimation(R.raw.differ)}
                     2->{lottieAnim.setAnimation(R.raw.calculator)}
                 }
                 if (position == titles.size-1){
                     btnStart.visibility = View.VISIBLE
                 }else{
                     btnStart.visibility = View.INVISIBLE
                 }
                 textTitle.text = titles[position]
                 textDesc.text = description[position]

                 btnStart.setOnClickListener {
                     startClick()
                 }
             }

         }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PagerBoardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount()=titles.size

}








