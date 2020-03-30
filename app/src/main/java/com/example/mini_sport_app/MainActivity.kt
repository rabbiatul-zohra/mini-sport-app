package com.example.mini_sport_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_stories.view.*
import okhttp3.*
import java.io.IOException
import okhttp3.OkHttpClient
import java.util.ArrayList
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Context
import okhttp3.internal.notify


class MainActivity : AppCompatActivity(), StoriesRecyclerViewAdapter.OnItemClickListener {
    private lateinit var storiesRecyclerViewAdapter: StoriesRecyclerViewAdapter
    private var items: List<Items> = ArrayList()
    private var imageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        fetchStoriesData()

    }


    private fun fetchStoriesData() {
        val url = "https://bbc.github.io/sport-app-dev-tech-challenge/data.json"

        val client = OkHttpClient()
        val handler = Handler()

        val request = Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                val gson = Gson()
                val storiesData: Stories = gson.fromJson(body, Stories::class.java)
                items = storiesData.data.items

                handler.post {
                    storiesRecyclerViewAdapter.storiesList(items)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("FAILED")
            }
        })
    }

    override fun onItemClicked(view: View, position: Int) {

        val intent = Intent(this, ArticleActivity::class.java).apply {
            val text = view.text_view.text
            val durationText = view.duration_text_view.text

            imageUrl = items[position].image?.large

            putExtra("TITLE", text)
            putExtra("DURATION_TITLE", durationText)
            putExtra("IMAGE_URL", imageUrl)
        }

        startActivity(intent)
    }

    private fun initRecyclerView() {
        val storyList = findViewById<RecyclerView>(R.id.recycler_view)
        storyList.layoutManager = LinearLayoutManager(this@MainActivity)
        storiesRecyclerViewAdapter = StoriesRecyclerViewAdapter(items, this)
        storyList.adapter = storiesRecyclerViewAdapter
    }
}
