package com.vedantika.ramanuajan

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vedantika.ramanuajan.databinding.ActivityGameBinding
import kotlin.random.Random

class MultiActivity : AppCompatActivity() {
    lateinit var viewbinding: ActivityGameBinding
    var correctanswer=0
    var livesleft=3
    var score=0
    lateinit var timer: CountDownTimer
    private val starTimerInMillies: Long = 60000
    var timeLeftInMillies:Long = starTimerInMillies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewbinding= ActivityGameBinding.inflate(layoutInflater)
        val view=viewbinding.root
        setContentView(view)

        setSupportActionBar(viewbinding.toolbar2)
        viewbinding.toolbar2.title = "Multiplication"

        question()
        viewbinding.buttonok.setOnClickListener {
            if(viewbinding.answer.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter the answer or click the NEXT button",Toast.LENGTH_LONG).show()
            }else{
                val useranswer=viewbinding.answer.text.toString().toInt()
                if(useranswer==correctanswer){
                    score+=10
                    viewbinding.scoredpoints.text=score.toString()
                    viewbinding.question.text="Correct answer...."
                }else{
                    livesleft-=1
                    viewbinding.lifesleft.text=livesleft.toString()
                    viewbinding.question.text="Wrong answer...."
                }
            }
        }
        viewbinding.buttonnxt.setOnClickListener {
            viewbinding.answer.text.clear()
            if(livesleft==0){
                Toast.makeText(this,"Game Over",Toast.LENGTH_LONG).show()
                val intent= Intent(this@MultiActivity,ResultActivity::class.java)
                intent.putExtra("SCORE: ",score)
                startActivity(intent)
                finish()
            }else{
                question()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun question(){
        if(::timer.isInitialized){
            timer.cancel()
        }
        timeLeftInMillies = starTimerInMillies
        val number1=Random.nextInt(0,100)
        val number2=Random.nextInt(0,100)
        viewbinding.question.text="$number1 * $number2"
        correctanswer=number1*number2
        starttimer()
    }

    fun starttimer(){
        timer=object: CountDownTimer(timeLeftInMillies,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillies=millisUntilFinished/1000
                viewbinding.timeleft.text=timeLeftInMillies.toString()
            }

            override fun onFinish() {
                viewbinding.timeleft.text="00"
                livesleft-=1
                viewbinding.lifesleft.text=livesleft.toString()
                if (livesleft <= 0) {
                    val intent = Intent(this@MultiActivity, ResultActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    viewbinding.question.text = "Time's up! Try the next one."
                }

            }
        }
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }
}