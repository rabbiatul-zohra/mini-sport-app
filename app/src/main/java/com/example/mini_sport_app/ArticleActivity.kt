package com.example.mini_sport_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_article)

        val textView = findViewById<TextView>(R.id.article_text_view)
        val articleDurationText = findViewById<TextView>(R.id.article_duration_text_view)

        textView.text = intent.getStringExtra("TITLE")
        articleDurationText.text = intent.getStringExtra("DURATION_TITLE")

        val imageView = findViewById<ImageView>(R.id.article_image_view)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(intent.getStringExtra("IMAGE_URL"))
            .into(imageView)
    }
}
