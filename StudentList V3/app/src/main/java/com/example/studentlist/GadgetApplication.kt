package com.example.studentlist

import android.app.Application

class GadgetApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        GadgetRepository.initialize(this)
    }

}