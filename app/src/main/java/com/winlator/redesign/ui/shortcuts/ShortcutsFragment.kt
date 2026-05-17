package com.winlator.redesign.ui.shortcuts

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
import androidx.recyclerview.widget.GridLayoutManager
import com.winlator.redesign.databinding.FragmentShortcutsBinding
import kotlinx.coroutines.launch

class ShortcutsFragment : Fragment() {

    private var _binding: FragmentShortcutsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShortcutsViewModel by viewModels()
    private lateinit var adapter: ShortcutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShortcutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeShortcuts()
    }

    private fun setupRecyclerView() {
        adapter = ShortcutAdapter(
            onLaunch = { shortcut ->
                Toast.makeText(requireContext(), "Launching ${shortcut.name}…", Toast.LENGTH_SHORT).show()
            },
            onDelete = { shortcut -> viewModel.delete(shortcut) }
        )
        binding.rvShortcuts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@ShortcutsFragment.adapter
        }
    }

    private fun setupFab() {
        binding.fabAddShortcut.setOnClickListener {
            Toast.makeText(requireContext(), "Add shortcut coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeShortcuts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.shortcuts.collect { shortcuts ->
                    adapter.submitList(shortcuts)
                    val isEmpty = shortcuts.isEmpty()
                    binding.rvShortcuts.visibility = if (isEmpty) View.GONE else View.VISIBLE
                    binding.layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
                    binding.tvShortcutsSubtitle.text =
                        "${shortcuts.size} game${if (shortcuts.size != 1) "s" else ""} & app${if (shortcuts.size != 1) "s" else ""}"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
