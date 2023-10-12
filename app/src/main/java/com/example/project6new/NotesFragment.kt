package com.example.project6new

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project6new.databinding.FragmentNotesBinding

/**
 * A fragment for displaying a list of notes.
 */
class NotesFragment : Fragment()   {
    val TAG = "NotesFragment"

    /**
     * View binding for the NotesFragment
     */
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    /**
     * Called to create and return the view hierarchy associated with this fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The created view.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(NotesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /**
         * Function to handle click on a note.
         */
        fun noteClicked (noteId : Long) {
            Log.d(TAG, "in noteClicked() : noteId = $noteId")
            viewModel.onNoteClicked(noteId)
        }

        /**
         * Function to handle 'Yes' button press for note deletion.
         */
        fun yesPressed(noteId : Long) {
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            binding.viewModel?.deleteNote(noteId)
        }

        /**
         * Function to handle click on the delete button for a note.
         */
        fun deleteClicked (noteId : Long) {
            Log.d(TAG, "in deleteClicked(): noteId = $noteId")
             ConfirmDeleteDialogFragment(noteId,::yesPressed).show(childFragmentManager,
                 ConfirmDeleteDialogFragment.TAG)
        }

        /**
         * Creates an adapter for the RecyclerView to handle note items.
         */
        val adapter = NoteItemAdapter(::noteClicked,::deleteClicked)

        binding.notesList.adapter = adapter

        /**
         * Observes changes in the notes list and update the RecyclerView.
         */
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        /**
         * Navigate to the EditNoteFragment when a note is clicked.
         */
        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId ->
            noteId?.let {
                val action = NotesFragmentDirections
                    .actionNotesFragmentToEditNoteFragment(noteId)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }
        })

        return view
    }

    /**
     * Called when the view previously created by onCreateView has been detached from the fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}