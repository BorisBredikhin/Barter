package ru.bredikhinpechnnikov.barter.data.model

data class Task(
    var id: Int? = null,
    var customer: Int? = null,
    var customer_str: String? = null,
    var executor: Int? = null,
    var executor_str: String? = null,
    var title: String,
    var description: String,
    var price: Int,
    var status: String? = null,
    var address: String
) {
}
