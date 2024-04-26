package com.eseo.noteapp.view.activity

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

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)

        val note : Note = intent.getSerializableExtra("UPDATE") as Note
        binding.titleEdit.setText(note.title)
        binding.textEdit.setText(note.text)
        note.title = binding.titleEdit.text.toString()
        note.text = binding.textEdit.text.toString()
        noteViewModel.updateNote(note)

    }
}