package com.example.studentlist

import android.app.Application

class SchoolApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SchoolRepository.initialize(this)
    }
}