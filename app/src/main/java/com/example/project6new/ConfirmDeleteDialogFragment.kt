package com.example.project6new

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment


/**
 * DialogFragment for displaying a confirmation dialog before deleting a note.
 *
 * This dialog prompts the user to confirm the deletion of a note, providing "Yes" and "No" options.
 *
 * @property noteId The numerical key associated with the Note object to be deleted.
 * @property clickListener A function that takes the noteId as a parameter and listens for user interactions.
 *
 * @return [DialogFragment] An instance of the dialog fragment to be displayed.
 */
class ConfirmDeleteDialogFragment(val noteId : Long,val clickListener: (noteId: Long) -> Unit) : DialogFragment() {

    interface myClickListener {
    }

    var listener: myClickListener? = null

    /**
     * Called to create the dialog and return the Dialog object.
     *
     * @param savedInstanceState The saved state of the variables.
     * @return Dialog The created dialog.
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        /**
         * Creates a Dialog object
         *
         * @param savedInstanceState: Bundle, saved state of the variables
         * @return Dialog
         */
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _,_ -> clickListener(noteId)}
            .setNegativeButton(getString(R.string.no)) { _,_ -> }
            .create()

    companion object {
        const val TAG = "ConfirmDeleteDialogFragment"
    }

    /**
     * Called when the fragment is attached to a context.
     *
     * @param context The context to which the fragment is attached.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }
}