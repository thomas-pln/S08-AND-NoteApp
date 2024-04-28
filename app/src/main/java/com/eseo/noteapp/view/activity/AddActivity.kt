package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.databinding.ActivityAddBinding

/**
 * Activity for adding a new note.
 *
 * @property binding The binding object that gives access to the views in the layout.
 */
class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add )

        // Set a click listener for the confirm button
        binding.confirmButton.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                Toast.makeText(applicationContext, "Both fields must be filled", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

                // Create a new note with the entered title and text
                val note = Note(
                    title = binding.titleEdit.text.toString(),
                    text = binding.textEdit.text.toString()
                )

                // Put the note in the reply intent as an extra
                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        /**
         * The key for the note data in the reply intent.
         */
        val EXTRA_REPLY: String = "com.eseo.android.noteapp.REPLY"
    }
}