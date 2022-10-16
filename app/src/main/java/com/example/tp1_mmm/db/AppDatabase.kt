package com.example.tp1_mmm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp1_mmm.dao.PersonDao
import com.example.tp1_mmm.models.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao
}