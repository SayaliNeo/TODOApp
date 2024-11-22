package com.moviecatalog.todoappcompose.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication:Application()
/*{
    override fun onCreate() {
        super.onCreate()


        startKoin{
            androidLogger()
            androidContext(this@NoteApplication)
            modules(appModule, databaseModule,domainModule, viewModelModule)
        }

    }
}*/