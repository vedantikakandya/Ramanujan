package com.vedantika.ramanuajan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vedantika.ramanuajan.databinding.ActivityGameBinding
import com.vedantika.ramanuajan.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var viewbinding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding= ActivityResultBinding.inflate(layoutInflater)
        val view=viewbinding.root
        setContentView(view)

        val score=intent.getIntExtra("SCORE: ",0)
        viewbinding.scorecard.text=score.toString()

        viewbinding.buttonplayagain.setOnClickListener {
            val intent= Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewbinding.buttonexit.setOnClickListener {
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}