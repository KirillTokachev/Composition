package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {
    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(it)
            }
            buttonLevelEasy.setOnClickListener {
                launchGameFragment(it)
            }
            buttonLevelNormal.setOnClickListener {
                launchGameFragment(it)
            }
            buttonLevelHard.setOnClickListener {
                launchGameFragment(it)
            }
        }
    }

    private fun launchGameFragment(view: View) {
        var level = when (view) {
            binding.buttonLevelTest -> Level.TEST
            binding.buttonLevelEasy -> Level.EASY
            binding.buttonLevelNormal -> Level.NORMAL
            binding.buttonLevelHard -> Level.HARD
            else -> {
                throw RuntimeException("No level")
            }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}