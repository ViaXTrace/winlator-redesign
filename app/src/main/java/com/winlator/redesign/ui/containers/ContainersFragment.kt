package com.winlator.redesign.ui.containers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.winlator.redesign.R
import com.winlator.redesign.data.model.Container
import com.winlator.redesign.databinding.FragmentContainersBinding
import com.winlator.redesign.ui.dialogs.CreateContainerDialog
import kotlinx.coroutines.launch

class ContainersFragment : Fragment() {

    private var _binding: FragmentContainersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContainersViewModel by viewModels()
    private lateinit var adapter: ContainerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContainersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeContainers()
    }

    private fun setupRecyclerView() {
        adapter = ContainerAdapter(
            onLaunch = { container -> launchContainer(container) },
            onSettings = { container -> openSettings(container) },
            onDelete = { container -> confirmDelete(container) },
            onClone = { container -> cloneContainer(container) }
        )
        binding.rvContainers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ContainersFragment.adapter
        }

        binding.btnCreateFirst.setOnClickListener { showCreateDialog() }
    }

    private fun setupFab() {
        binding.fabAddContainer.setOnClickListener { showCreateDialog() }
    }

    private fun showCreateDialog() {
        CreateContainerDialog { name, screenSize, driver, dxWrapper ->
            viewModel.createContainer(name, screenSize, driver, dxWrapper)
            Toast.makeText(requireContext(), "Container '$name' created", Toast.LENGTH_SHORT).show()
        }.show(parentFragmentManager, "create_container")
    }

    private fun launchContainer(container: Container) {
        Toast.makeText(
            requireContext(),
            "Launching '${container.name}'…",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun openSettings(container: Container) {
        Toast.makeText(
            requireContext(),
            "Settings for '${container.name}'",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun cloneContainer(container: Container) {
        viewModel.cloneContainer(container)
        Toast.makeText(requireContext(), "Container cloned", Toast.LENGTH_SHORT).show()
    }

    private fun confirmDelete(container: Container) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.container_delete_confirm)
            .setMessage(R.string.container_delete_confirm_sub)
            .setNegativeButton(R.string.dialog_cancel) { _, _ -> }
            .setPositiveButton(R.string.container_delete) { _, _ ->
                viewModel.deleteContainer(container)
            }
            .show()
    }

    private fun observeContainers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.containers.collect { containers ->
                    adapter.submitList(containers)
                    val isEmpty = containers.isEmpty()
                    binding.rvContainers.visibility = if (isEmpty) View.GONE else View.VISIBLE
                    binding.layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
                    binding.tvContainersSubtitle.text =
                        "${containers.size} environment${if (containers.size != 1) "s" else ""}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
