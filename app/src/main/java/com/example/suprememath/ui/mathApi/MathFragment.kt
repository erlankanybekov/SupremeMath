package com.example.suprememath.ui.mathApi

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.suprememath.R
import com.example.suprememath.databinding.FragmentMathApiBinding

class MathFragment : Fragment() {
    private lateinit var binding: FragmentMathApiBinding

    private lateinit var mathViewModel: MathViewModel
    private val rotateOpen:Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open_anim) }
    private val rotateClose:Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_anim) }
    private val toBottom:Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_anim) }

    private var clicked = false

    val request_img=21


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMathApiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mathViewModel= ViewModelProvider(this)[MathViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnSend.setOnClickListener{
            mathViewModel.mathExpress(binding.editText.text.toString().trim()).observe(viewLifecycleOwner,{
                binding.tvResult?.text = "$it"
            })
        }
        binding.editBtn.setOnClickListener {

        }
        binding.floatingBtn.setOnClickListener {
            onBtnClicked()
        }
        binding.cameraBtn.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent,request_img)

            }catch (e: ActivityNotFoundException){
                Toast.makeText(requireContext(),"не получилось  ${e.localizedMessage}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == request_img && resultCode == RESULT_OK ){
            val imageBitmap = data?.extras?.get("data") as Bitmap
          //  binding.img.setImageBitmap(imageBitmap)
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }
    }

    private fun onBtnClicked() {
        setVisibility(clicked)
        setAnim(clicked)
        setClickable(clicked)
        clicked=!clicked
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked){
            binding.cameraBtn.isClickable = true
            binding.editBtn.isClickable = true
        }
        else{
            binding.editBtn.isClickable = false
            binding.cameraBtn.isClickable = false
        }
    }

    private fun setAnim(clicked:Boolean) {
        if (!clicked){
            binding.editBtn.startAnimation(fromBottom)
            binding.cameraBtn.startAnimation(fromBottom)
            binding.floatingBtn.startAnimation(rotateOpen)
        }else{
            binding.editBtn.startAnimation(toBottom)
            binding.cameraBtn.startAnimation(toBottom)
            binding.floatingBtn.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            binding.cameraBtn.visibility = View.VISIBLE
            binding.editBtn.visibility =View.VISIBLE
        }
        else{
            binding.editBtn.visibility =View.INVISIBLE
            binding.cameraBtn.visibility = View.INVISIBLE
        }

    }

}