package com.example.project6new

import androidx.recyclerview.widget.DiffUtil

/**
 * DiffUtil.ItemCallback implementation for comparing Note objects in a RecyclerView.
 *
 * This class defines how to compare items for use in DiffUtil to efficiently update a RecyclerView's data.
 */
class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {
    /**
     * Checks if two Note objects represent the same item.
     *
     * @param oldItem The old Note object.
     * @param newItem The new Note object.
     * @return True if the items are the same, otherwise false.
     */
    override fun areItemsTheSame(oldItem: Note, newItem: Note)
            = (oldItem.noteId == newItem.noteId)

    /**
     * Checks if the contents of two Note objects are the same.
     *
     * @param oldItem The old Note object.
     * @param newItem The new Note object.
     * @return True if the contents are the same, otherwise false.
     */
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = (oldItem == newItem)
}