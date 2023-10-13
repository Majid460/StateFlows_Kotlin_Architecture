package com.example.testapp.utils

import android.content.Context
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.testapp.R

class MentionEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatEditText(context, attrs, defStyle) {

    private val mentionColor = ContextCompat.getColor(context, R.color.mentionColor)
    private val changeListener =  object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            handleMentions(editable)
        }
    }
    init {
        addTextChangedListener (changeListener)
    }
    private fun handleMentions(editable: Editable?) {
        val text = editable.toString()
        val spannable = SpannableStringBuilder(text)

        val mentionPattern = Regex("(?<=@)\\w+")

        val mentions = mentionPattern.findAll(text)
        for (mention in mentions) {
            val start = mention.range.first
            val end = mention.range.last + 1

            // Apply the mention color to the tagged member
            spannable.setSpan(
                ForegroundColorSpan(mentionColor),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        removeTextChangedListener(changeListener)
        editable?.replace(0, editable.length, spannable)
        addTextChangedListener(changeListener)
    }
}