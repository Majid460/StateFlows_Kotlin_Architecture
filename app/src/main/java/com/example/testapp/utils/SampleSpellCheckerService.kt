package com.example.testapp.utils

import android.service.textservice.SpellCheckerService

class SampleSpellCheckerService : SpellCheckerService(){
    override fun createSession(): Session {
        return SpellCheckerSession()
    }
}