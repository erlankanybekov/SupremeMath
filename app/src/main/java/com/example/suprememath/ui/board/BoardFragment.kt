package com.example.suprememath.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.suprememath.R
import com.example.suprememath.utils.Prefs
import com.example.suprememath.databinding.FragmentBoardBinding

class BoardFragment : Fragment(R.layout.fragment_board) {

    private val binding: FragmentBoardBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentBoardBinding.inflate(layoutInflater)
    }
    private var prefs: Prefs? = null

    private val adapter by lazy {
        BoardAdapter(this::startClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
    }

    private fun initPager() {
        binding.viewPager.adapter = adapter
        val dots = binding.wormDotsIndicator
        val viewPager2 = binding.viewPager
        dots.setViewPager2(viewPager2)
    }

    private fun startClick() {
        findNavController().navigateUp()
        prefs = Prefs(requireContext())
        prefs?.saveState()
    }
}