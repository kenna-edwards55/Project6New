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
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment()   {
    val TAG = "NotesFragment"
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
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

       /* val adapter = NoteItemAdapter{ noteId ->
            viewModel.onNoteClicked(noteId)

        }*/

        fun noteClicked (noteId : Long) {
            Log.d(TAG, "in noteClicked() : noteId = $noteId")
            viewModel.onNoteClicked(noteId)
        }


        fun yesPressed(noteId : Long) {
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            binding.viewModel?.deleteNote(noteId)
        }
        fun deleteClicked (noteId : Long) {
             ConfirmDeleteDialogFragment(noteId,::yesPressed).show(childFragmentManager,
                 ConfirmDeleteDialogFragment.TAG)
        }
        val adapter = NoteItemAdapter(::noteClicked,::deleteClicked)

        binding.notesList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}