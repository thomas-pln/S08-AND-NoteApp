package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityNoteBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.adapter.NoteAdapter
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory

/**
 * Activity for displaying and managing notes.
 *
 * @property newNoteActivityRequestCode The request code for starting the AddActivity.
 * @property noteViewModel The ViewModel that is used to interact with the data layer.
 * @property binding The binding object that gives access to the views in the layout.
 * @property noteAdapter The adapter that is used to display the notes in a RecyclerView.
 */
class NoteActivity : AppCompatActivity() {

    private val newNoteActivityRequestCode = 1

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    private lateinit var binding: ActivityNoteBinding
    private lateinit var noteAdapter: NoteAdapter

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        noteAdapter = NoteAdapter(noteViewModel)

        setNoteRecycler()

        noteViewModel.notesLiveData.observe(this, Observer { notes ->
            noteAdapter.loadNotes(notes)
        })

        binding.addNewNote.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    /**
     * Sets up the RecyclerView that is used to display the notes.
     */
    private fun setNoteRecycler() {
        binding.noteRecycler.apply {
            adapter = noteAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with, the resultCode it returned, and any additional data from it.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(), allowing you to identify who this result came from.
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param intentData An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val note =  intentData?.getSerializableExtra(AddActivity.EXTRA_REPLY) as Note
            noteViewModel.insertNote(note)

        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}