package com.example.project6new

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project6new.databinding.NoteItemBinding

/**
 * An adapter for displaying a list of notes in a RecyclerView.
 *
 * @param clickListener A lambda function to handle item click events.
 * @param deleteClickListener A lambda function to handle item delete click events.
 */
class NoteItemAdapter(val clickListener: (noteId: Long) -> Unit,
                      val deleteClickListener: (noteId: Long) -> Unit)
    : ListAdapter<Note, NoteItemAdapter.NoteItemViewHolder>(NoteDiffItemCallback()) {

    /**
     * Creates and returns a new NoteItemViewHolder for the RecyclerView.
     *
     * @param parent The parent ViewGroup in which the ViewHolder will be created.
     * @param viewType The type of the view.
     * @return A new NoteItemViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : NoteItemViewHolder = NoteItemViewHolder.inflateFrom(parent)

    /**
     * Binds data to the ViewHolder at the specified position in the RecyclerView.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener, deleteClickListener)
    }

    /**
     * ViewHolder class for individual note items in the RecyclerView.
     *
     * @param binding The data binding for the layout.
     */
    class NoteItemViewHolder(val binding: NoteItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            /**
            * Inflates a NoteItemViewHolder from the provided parent ViewGroup.
            *
            * @param parent The parent ViewGroup for inflating the ViewHolder.
            * @return A new NoteItemViewHolder.
            */
            fun inflateFrom(parent: ViewGroup): NoteItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
                return NoteItemViewHolder(binding)
            }
        }

        /**
         * Binds a note item to the ViewHolder and sets click listeners for item and delete button.
         *
         * @param item The Note object to bind to the ViewHolder.
         * @param clickListener A lambda function to handle item click events.
         * @param deleteClickListener A lambda function to handle item delete click events.
         */
        fun bind(item: Note, clickListener: (noteId: Long) -> Unit,
                 deleteClickListener: (noteId: Long) -> Unit) {
            binding.note = item
            binding.root.setOnClickListener { clickListener(item.noteId) }
            binding.deleteButton.setOnClickListener { deleteClickListener(item.noteId) }
        }
    }
}