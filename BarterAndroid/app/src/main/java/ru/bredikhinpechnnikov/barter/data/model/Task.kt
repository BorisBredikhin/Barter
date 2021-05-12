package ru.bredikhinpechnnikov.barter.data.model

data class Task(
    var id: Int? = null,
    var customer: Int? = null,
    var executor: Int? = null,
    var title: String,
    var description: String,
    var price: Int,
    var status: String? = null,
    var address: String
) {
}
