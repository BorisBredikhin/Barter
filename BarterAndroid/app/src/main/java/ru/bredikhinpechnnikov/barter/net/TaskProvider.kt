package ru.bredikhinpechnnikov.barter.net

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import ru.bredikhinpechnnikov.barter.data.model.Task

object TaskProvider {
    fun addNewTask(task: Task, token: String) {
        var resp: String? = null

        val map = HashMap<String, Any>()

        map["title"] = task.title
        map["description"] = task.description
        map["price"] = task.price
        map["address"] = task.address

        val obj = JSONObject(map as Map<*, *>)

        val body = obj.toString().toRequestBody(JSON)

        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(
                    Request
                        .Builder()
                        .url(Config.BACKEND_ADDRESS+"/api/tasks/new")
                        .header("Authorization", "token $token")
                        .post(body)
                        .build()
                )
                .execute()
                .use {
                    resp = it.body!!.string()
                }
        })

        thr.start()
        thr.join()
    }
}