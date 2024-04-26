package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add )

        binding.confirmButton.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

                val note = Note(
                    title = binding.titleEdit.text.toString(),
                    text = binding.textEdit.text.toString()
                )

                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        val EXTRA_REPLY: String = "com.eseo.android.noteapp.REPLY"
    }
}