package com.example.tp1_mmm.models

data class Person (
    val firstName: String,
    val lastName: String,
    val birthPlace: String
) {
    companion object {
        public val aList: List<Person>
            get() = listOf(
                Person("Edsger", "Dijkstra", "Rotterdam"),
                Person("Kenneth", "Thompson", "New Orleans"),
                Person("Larry", "Wall", "Los Angeles"),
                Person("Donald", "Knuth", "Milwaukee")
            )
    }
}