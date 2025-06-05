package com.vedantika.ramanuajan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vedantika.ramanuajan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var binding: ActivityMainBinding
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        setSupportActionBar(binding.materialToolbar)
        binding.materialToolbar.title = "Ramanuajan"

        binding.buttonaddition.setOnClickListener {
            val intent= Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)
        }
        binding.buttonsubtraction.setOnClickListener {
            val intent= Intent(this@MainActivity,SubActivity::class.java)
            startActivity(intent)
        }
        binding.buttonmultoplication.setOnClickListener {
            val intent= Intent(this@MainActivity,MultiActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}