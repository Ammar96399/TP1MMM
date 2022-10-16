package com.example.tp1_mmm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tp1_mmm.models.Person

@Dao
interface PersonDao {
    @Query("select * from person")
    suspend fun getAll(): List<Person>

    @Query("select * from person where uid in (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<Person>

    @Query("select * from person where firstName like :first and lastName like :last limit 1")
    suspend fun findByName(first: String, last: String): Person

    @Insert
    suspend fun insertAll(vararg persons: Person)

    @Delete
    suspend fun delete(person: Person)
}