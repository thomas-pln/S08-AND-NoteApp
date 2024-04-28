package com.eseo.noteapp.view.activity

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityUpdateBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory

/**
 * Activity for updating an existing note.
 *
 * @property binding The binding object that gives access to the views in the layout.
 * @property noteViewModel The ViewModel that is used to interact with the data layer.
 */
class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        val note : Note = intent.getSerializableExtra("UPDATE") as Note
        binding.titleEdit.setText(note.title)
        binding.textEdit.setText(note.text)

        // Set a click listener for the confirm button
        binding.confirmButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                Toast.makeText(applicationContext, "Both fields must be filled", Toast.LENGTH_LONG).show()
            } else {
                note.title = binding.titleEdit.text.toString()
                note.text = binding.textEdit.text.toString()
                noteViewModel.updateNote(note)
                finish()
            }
        }

        // Set a click listener for the cancel button
        binding.cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}