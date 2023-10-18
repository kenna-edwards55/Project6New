# Project 6 Notes App

Description of the project ...
* Notes application that allows users to create, edit, and manage notes. 

## Functionality 

The following **required** functionality is completed:

* [ ] **List of Notes**: Upon opening the app, the user is presented with a list of existing notes (if they have them.) Each note is displayed with its title.
* [ ] **Creating a New Note**: User can tap the "Add Note" button, which will navigate to the EditNoteFragment. Here, the user can add/change the name and description of the note.
* [ ] **Editing an Existing Note**: User can press the name of the note. This will navigate to EditNoteFragment.
* [ ] **Deleting a Note**: User can press the red X button. A confirmation dialog will appear and ask whether to delete the note with a Yes/No.
* [ ] **Database** All notes are stored in a local SQLite database and the Room Persistence Library is used for managing database operations.
* [ ] **ViewModel and LiveData** Used to separate the UI-related data and business logic. 

The following **extensions** are implemented:
* androidx.fragment.app.DialogFragment
* androidx.fragment.app.Fragment
* androidx.lifecycle.Observer
* androidx.lifecycle.ViewModelProvider
* androidx.navigation.findNavController
* androidx.lifecycle.LiveData
* androidx.lifecycle.MutableLiveData
* androidx.lifecycle.ViewModel
* androidx.lifecycle.viewModelScope
* androidx.appcompat.app.AppCompatActivity
* androidx.room.ColumnInfo
* androidx.room.Entity
* androidx.room.PrimaryKey
* androidx.room.Dao
* androidx.room.Delete
* androidx.room.Insert
* androidx.room.Query
* androidx.room.Update
* androidx.room.Database
* androidx.room.Room
* androidx.room.RoomDatabase
* androidx.recyclerview.widget.DiffUtil
* androidx.recyclerview.widget.ListAdapter
* androidx.recyclerview.widget.RecyclerView
* androidx.lifecycle.LiveData
* androidx.lifecycle.MutableLiveData
* androidx.lifecycle.ViewModel
* androidx.lifecycle.viewModelScope
* kotlinx.coroutines.launch

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/kenna-edwards55/Project6New/blob/master/Project6Demo.gif' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
* A major challenge I faced was navigating directly to the EditNoteFragment.  Reason being, when a Note was created, it is given an ID of 0. However, when it is added to the database, the ID changes.  Therefore, I added a return to the insert method in NoteDao.kt, which will return the ID that the Note was assigned.

Logic
* Room Database: Room is used to manage the SQLite database. This includes defining a NoteDao for CRUD (Create, Read, Update, Delete) operations and a NoteDatabase for managing the database instance.
* LiveData: LiveData is utilized for observing and updating changes in the list of notes and navigation states. It ensures a responsive and dynamic user interface.
* ViewModels: Two primary ViewModel classes:
*     NotesViewModel: This ViewModel manages the main notes screen, handling note creation, editing, deletion, and navigation. It observes changes in the list of notes and facilitates navigation to edit notes.
*     EditNoteViewModel: Responsible for managing the editing of individual notes. It ensures changes are saved and navigates back to the notes list.
* Navigation: Utilizes the Navigation Component to facilitate smooth transitions between the notes list, note editing, and the confirmation dialog for deleting notes.

## License

    Copyright [2023] [Kenna Edwards]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
