package com.winlator.redesign.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.winlator.redesign.BuildConfig
import com.winlator.redesign.R
import com.winlator.redesign.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSettingRows()
        setupAbout()
    }

    private fun setupSettingRows() {
        with(binding.settingCursorColor) {
            ivSettingIcon.setImageResource(R.drawable.ic_settings)
            tvSettingTitle.text = getString(R.string.settings_cursor_color)
            tvSettingSubtitle.text = "White"
        }

        with(binding.settingCursorScale) {
            ivSettingIcon.setImageResource(R.drawable.ic_settings)
            tvSettingTitle.text = getString(R.string.settings_cursor_scale)
            tvSettingSubtitle.text = "1.0x"
        }

        with(binding.settingShowFps) {
            ivToggleIcon.setImageResource(R.drawable.ic_info)
            tvToggleTitle.text = getString(R.string.settings_show_fps)
            tvToggleSubtitle.text = "Display FPS overlay during gameplay"
        }

        with(binding.settingWineDebug) {
            ivToggleIcon.setImageResource(R.drawable.ic_wine)
            tvToggleTitle.text = getString(R.string.settings_wine_debug)
            tvToggleSubtitle.text = "Enable WINEDEBUG logging"
        }

        with(binding.settingBox64Logs) {
            ivSettingIcon.setImageResource(R.drawable.ic_cpu)
            tvSettingTitle.text = getString(R.string.settings_box64_logs)
            tvSettingSubtitle.text = "Off"
        }

        with(binding.settingSaveLogs) {
            ivToggleIcon.setImageResource(R.drawable.ic_storage)
            tvToggleTitle.text = getString(R.string.settings_save_logs)
            tvToggleSubtitle.text = "Save logs to /sdcard/Winlator/logs"
        }
    }

    private fun setupAbout() {
        binding.tvVersion.text = getString(R.string.settings_about_version, BuildConfig.VERSION_NAME)

        binding.btnGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ViaXTrace/winlator-redesign"))
            startActivity(intent)
        }

        binding.btnDonate.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ViaXTrace"))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
