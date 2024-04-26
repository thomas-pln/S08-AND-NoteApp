package com.eseo.noteapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.activity.UpdateActivity
import com.eseo.noteapp.viewmodel.NoteViewModel

class NoteAdapter(
    private val noteViewModel: NoteViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var notes : MutableList<Note>
    init {
        notes = mutableListOf()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadNotes(notes: List<Note>?) {
        this.notes = notes as MutableList<Note>
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var noteTitle : TextView
        private var noteText : TextView
        private var editNoteImage : ImageView
        private var deleteNoteImage : ImageView

        init {
            noteTitle = itemView.findViewById(R.id.noteTitle)
            noteText = itemView.findViewById(R.id.noteText)
            editNoteImage = itemView.findViewById(R.id.editNoteImage)
            deleteNoteImage = itemView.findViewById(R.id.deleteNoteImage)
        }

        fun bindNote(note: Note){
            noteText.text = note.text
            noteTitle.text = note.title
            editNoteImage.setOnClickListener {

                val context : Context = itemView.context
                val noteIntent = Intent(context, UpdateActivity::class.java)
                noteIntent.putExtra("UPDATE", note)
                context.startActivity(noteIntent)

            }

            deleteNoteImage.setOnClickListener {
                noteViewModel.deleteNote(note.id)
            }
        }
    }
}

