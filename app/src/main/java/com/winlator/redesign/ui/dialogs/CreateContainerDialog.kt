package com.winlator.redesign.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.winlator.redesign.databinding.DialogCreateContainerBinding

class CreateContainerDialog(
    private val onCreate: (name: String, screenSize: String, driver: String, dxWrapper: String) -> Unit
) : DialogFragment() {

    private var _binding: DialogCreateContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCreateContainerBinding.inflate(layoutInflater)

        setupDropdowns()

        binding.btnClose.setOnClickListener { dismiss() }
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.btnCreate.setOnClickListener {
            val name = binding.etContainerName.text.toString().trim()
            if (name.isEmpty()) {
                binding.tilName.error = "Name is required"
                return@setOnClickListener
            }
            binding.tilName.error = null
            val screenSize = binding.actvScreenSize.text.toString().replace(" ", "")
                .let { if (it.matches(Regex("\\d+x\\d+"))) it else "1280x720" }
            val driver = when (binding.actvGraphicsDriver.text.toString()) {
                "Turnip + Gladio" -> "turnip,gladio"
                "Zink + Gladio" -> "zink,gladio"
                "VirGL" -> "virgl"
                else -> "vortek,gladio"
            }
            val dxWrapper = when (binding.actvDxwrapper.text.toString()) {
                "VKD3D" -> "vkd3d"
                "WineD3D" -> "wined3d"
                "CNC DDraw" -> "cnc-ddraw"
                else -> "dxvk"
            }
            onCreate(name, screenSize, driver, dxWrapper)
            dismiss()
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()
            .apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
    }

    private fun setupDropdowns() {
        val screenSizes = listOf("1280x720", "1920x1080", "2560x1440", "1024x768", "800x600")
        binding.actvScreenSize.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, screenSizes)
        )

        val drivers = listOf("Vortek + Gladio", "Turnip + Gladio", "Zink + Gladio", "VirGL")
        binding.actvGraphicsDriver.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, drivers)
        )

        val dxWrappers = listOf("DXVK", "VKD3D", "WineD3D", "CNC DDraw")
        binding.actvDxwrapper.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, dxWrappers)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
