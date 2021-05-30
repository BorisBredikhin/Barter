package ru.bredikhinpechnnikov.barter.net

import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_AUTO
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import ru.bredikhinpechnnikov.barter.data.model.Task
import java.util.*

object TaskProvider {
    fun execute_task(taskId: Int, token: String) {
        var resp: String? = null

        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(
                    Request
                        .Builder()
                        .url(Config.BACKEND_ADDRESS+"/api/tasks/execute?pk=$taskId")
                        .header("Authorization", "token $token")
                        .get()
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

    fun getTasks(token: String): ArrayList<Task> {
        var resp: String? = null

        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(
                    Request
                        .Builder()
                        .url(Config.BACKEND_ADDRESS+"/api/tasks")
                        .header("Authorization", "token $token")
                        .get()
                        .build()
                )
                .execute()
                .use {
                    resp = it.body!!.string()
                }
        })

        thr.start()
        thr.join()

        val resultTasks = ArrayList<Task>()

        try {
            val jsonObj = JSONObject(resp!!)
            val tasks = jsonObj.getJSONArray("tasks")

            for (i in 0..tasks.length() - 1) {
                val currentTask = tasks[i] as JSONObject
                resultTasks.add(JSONObjectToTask(currentTask))
            }
        } finally {

        }

        return resultTasks
    }

    fun getTask(token: String, id: Int): Task {
        var resp: String? = null

        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(
                    Request
                        .Builder()
                        .url(Config.BACKEND_ADDRESS+"/api/task?pk="+id.toString())
                        .header("Authorization", "token $token")
                        .get()
                        .build()
                )
                .execute()
                .use {
                    resp = it.body!!.string()
                }
        })

        thr.start()
        thr.join()

        val jsonObj = JSONObject(resp!!)

        return JSONObjectToTask(jsonObj)
    }

    private fun JSONObjectToTask(currentTask: JSONObject) = Task(
        id = currentTask.getInt("id"),
        customer = currentTask.getInt("customer"),
        executor = try {
            currentTask.getInt("executor")
        } catch (e: JSONException) {
            null
        },
        title = currentTask.getString("title"),
        description = currentTask.getString("description"),
        price = currentTask.getInt("price"),
        status = currentTask.getString("status"),
        address = currentTask.getString("address")
    )
}