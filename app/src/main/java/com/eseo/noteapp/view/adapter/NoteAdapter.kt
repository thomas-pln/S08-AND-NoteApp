package com.eseo.noteapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.activity.UpdateActivity
import com.eseo.noteapp.viewmodel.NoteViewModel
/**
 * Adapter for the RecyclerView that displays the notes.
 *
 * @property noteViewModel The ViewModel that is used to interact with the data layer.
 * @property notes The list of notes that is displayed in the RecyclerView.
 */
class NoteAdapter(
    private val noteViewModel: NoteViewModel
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var notes : MutableList<Note>

    init {
        notes = mutableListOf()
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(noteView)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return notes.size
    }

    /**
     * Loads the notes into the adapter and notifies any registered observers that the data set has changed.
     *
     * @param notes The list of notes to be loaded into the adapter.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun loadNotes(notes: List<Note>?) {
        this.notes = notes as MutableList<Note>
        notifyDataSetChanged()
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     *
     * @property itemView The view that represents the data for this ViewHolder.
     * @property noteTitle The TextView that displays the title of the note.
     * @property noteText The TextView that displays the text of the note.
     * @property editNoteImage The ImageView that represents the edit action for the note.
     * @property deleteNoteImage The ImageView that represents the delete action for the note.
     */
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

        /**
         * Binds the note data to the ViewHolder.
         *
         * @param note The note to be bound to the ViewHolder.
         */
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
                Toast.makeText(itemView.context, "Note has been deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
