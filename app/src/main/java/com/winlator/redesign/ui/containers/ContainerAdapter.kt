package com.winlator.redesign.ui.containers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.winlator.redesign.R
import com.winlator.redesign.data.model.Container
import com.winlator.redesign.databinding.ItemContainerBinding

class ContainerAdapter(
    private val onLaunch: (Container) -> Unit,
    private val onSettings: (Container) -> Unit,
    private val onDelete: (Container) -> Unit,
    private val onClone: (Container) -> Unit
) : ListAdapter<Container, ContainerAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(container: Container) {
            binding.tvContainerName.text = container.name
            binding.tvContainerStatus.text = if (container.isRunning) "Running" else "Stopped"
            binding.tvDriver.text = container.displayDriver
            binding.tvDxwrapper.text = container.displayDxWrapper
            binding.tvPreset.text = container.displayPreset

            val statusColor = if (container.isRunning)
                binding.root.context.getColor(R.color.color_status_running)
            else
                binding.root.context.getColor(R.color.color_status_stopped)

            binding.viewStatusDot.background.setTint(statusColor)
            binding.tvContainerStatus.setTextColor(statusColor)

            binding.btnLaunch.setOnClickListener { onLaunch(container) }
            binding.btnSettings.setOnClickListener { onSettings(container) }
            binding.btnContainerMenu.setOnClickListener { view ->
                PopupMenu(view.context, view).apply {
                    menuInflater.inflate(R.menu.container_context_menu, menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.action_clone -> { onClone(container); true }
                            R.id.action_delete -> { onDelete(container); true }
                            else -> false
                        }
                    }
                    show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContainerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Container>() {
        override fun areItemsTheSame(oldItem: Container, newItem: Container) =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Container, newItem: Container) =
            oldItem == newItem
    }
}
