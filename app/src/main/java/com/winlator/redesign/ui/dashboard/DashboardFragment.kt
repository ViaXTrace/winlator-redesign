package com.winlator.redesign.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.winlator.redesign.R
import com.winlator.redesign.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() {
        binding.btnNewContainer.setOnClickListener {
            findNavController().navigate(R.id.navigation_containers)
        }

        binding.btnAddShortcut.setOnClickListener {
            findNavController().navigate(R.id.navigation_shortcuts)
        }

        // System info rows
        setupSystemInfoRows()
    }

    private fun setupSystemInfoRows() {
        with(binding.rowWine) {
            ivRowIcon.setImageResource(R.drawable.ic_wine)
            tvRowLabel.text = getString(R.string.dashboard_wine_version)
            tvRowValue.text = "Wine 10.10"
        }
        with(binding.rowBox64) {
            ivRowIcon.setImageResource(R.drawable.ic_cpu)
            tvRowLabel.text = getString(R.string.dashboard_box64_version)
            tvRowValue.text = "Box64 0.3.2"
        }
        with(binding.rowGpu) {
            ivRowIcon.setImageResource(R.drawable.ic_gpu)
            tvRowLabel.text = getString(R.string.dashboard_gpu_driver)
            tvRowValue.text = "Turnip / Vortek"
        }
        with(binding.rowRootfs) {
            ivRowIcon.setImageResource(R.drawable.ic_storage)
            tvRowLabel.text = getString(R.string.dashboard_rootfs_version)
            tvRowValue.text = "v19"
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.containerCount.collect { count ->
                        binding.tvContainersCount.text = count.toString()
                    }
                }
                launch {
                    viewModel.shortcutCount.collect { count ->
                        binding.tvShortcutsCount.text = count.toString()
                    }
                }
                launch {
                    viewModel.activeCount.collect { count ->
                        binding.tvActiveCount.text = count.toString()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
