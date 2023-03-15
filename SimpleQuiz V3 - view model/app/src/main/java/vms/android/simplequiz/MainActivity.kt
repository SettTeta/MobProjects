package vms.android.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questionViewModel: QuestionViewModel by lazy {
        ViewModelProvider(this).get(QuestionViewModel::class.java)
    }

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d(TAG, "onCreate() called")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()

        yesButton.setOnClickListener {
            if (!questionViewModel.currentQuestionIsAnswered) {
                checkAnswer(true)
                disableButton()
            }
        }

        noButton.setOnClickListener {
            if (!questionViewModel.currentQuestionIsAnswered) {
                checkAnswer(false)
                disableButton()
            }
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }

        displayedQuestion.setOnClickListener {
            nextQuestion()
        }

        previousButton.setOnClickListener {
            questionViewModel.moveToPrevQuestion()
            updateQuestion()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPaused() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun nextQuestion() {
        questionViewModel.moveToNextQuestion()
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionViewModel.currentQuestionText
        displayedQuestion.setText(questionTextResId)
        if(questionViewModel.currentQuestionIsAnswered){
            disableButton()
        } else {
            yesButton.isEnabled = true
            noButton.isEnabled = true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        questionViewModel.answerQuestion(userAnswer)

        if(questionViewModel.currentAnsweredQuestion == questionViewModel.currentBankSize){
            val score = ((questionViewModel.currentCorrectAnswer.toFloat() / questionViewModel.currentBankSize.toFloat()) * 100).toInt()
            val scoreString = "You have answered all the questions. Your Score is $score%"
            Toast.makeText(this, scoreString, Toast.LENGTH_LONG).show()
        }
    }

    private fun disableButton(){
        yesButton.isEnabled = false
        noButton.isEnabled = false
    }
}

