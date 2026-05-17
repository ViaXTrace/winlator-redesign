package com.winlator.redesign.ui.controls

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.winlator.redesign.databinding.FragmentInputControlsBinding

class InputControlsFragment : Fragment() {

    private var _binding: FragmentInputControlsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputControlsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutEmpty.visibility = View.VISIBLE
        binding.fabAddProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Input control editor coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
