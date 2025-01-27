package com.example.bookstoreproject

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookstoreproject.data.models.room.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .createFromAsset("create_db.sql")
            .fallbackToDestructiveMigration() // Удаляет старую базу, если структура изменена
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("RoomDB", "Database created successfully.")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d("RoomDB", "Database opened.")
                }
            })
            .build()

    }


}