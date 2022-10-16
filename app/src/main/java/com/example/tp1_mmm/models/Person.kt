package com.example.tp1_mmm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
    val firstName: String,
    val lastName: String,
    val birthPlace: String
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
//    companion object {
//        public val aList: List<Person>
//            get() = listOf(
//                Person("Edsger", "Dijkstra", "Rotterdam"),
//                Person("Kenneth", "Thompson", "New Orleans"),
//                Person("Larry", "Wall", "Los Angeles"),
//                Person("Donald", "Knuth", "Milwaukee")
//            )
//    }
}