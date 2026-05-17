package com.winlator.redesign.ui.shortcuts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.winlator.redesign.data.model.Shortcut
import com.winlator.redesign.databinding.ItemShortcutBinding

class ShortcutAdapter(
    private val onLaunch: (Shortcut) -> Unit,
    private val onDelete: (Shortcut) -> Unit
) : ListAdapter<Shortcut, ShortcutAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemShortcutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shortcut: Shortcut) {
            binding.tvShortcutName.text = shortcut.name
            binding.tvShortcutContainer.text = shortcut.containerName
            binding.btnLaunchShortcut.setOnClickListener { onLaunch(shortcut) }
            binding.root.setOnLongClickListener { onDelete(shortcut); true }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShortcutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Shortcut>() {
        override fun areItemsTheSame(oldItem: Shortcut, newItem: Shortcut) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Shortcut, newItem: Shortcut) = oldItem == newItem
    }
}
