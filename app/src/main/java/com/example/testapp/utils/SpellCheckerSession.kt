package com.example.testapp.utils


import android.service.textservice.SpellCheckerService
import android.view.textservice.SuggestionsInfo
import android.view.textservice.TextInfo

class SpellCheckerSession : SpellCheckerService.Session() {
    override fun onCreate() {

    }

    override fun onGetSuggestions(p0: TextInfo?, p1: Int): SuggestionsInfo {
        val word: String? = p0?.text
        var suggestions: Array<String>? = null
        suggestions = if (word == "Peter") {
            arrayOf("Pedro", "Pietro", "Petar", "Pierre", "Petrus")
        } else {
            arrayOf()
        }
        return SuggestionsInfo(SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO, suggestions)
    }
}