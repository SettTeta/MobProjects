package sett_teta.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val questionBank = listOf(Question(R.string.question1, false),
                              Question(R.string.question2, true),
                              Question(R.string.question3, false),
                              Question(R.string.question4, false),
                              Question(R.string.question5, false),
                              Question(R.string.question6, false),
                              Question(R.string.question7, true),
                              Question(R.string.question8, true),
                              Question(R.string.question9, true),
                              Question(R.string.question10, false))

    var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayedQuestion.setText(questionBank[currentIndex].questionTextId)

        displayedQuestion.setOnClickListener {
            nextQuestion(1)
        }

        noButton.setOnClickListener  {
            checkAnswer(false)
        }

        yesButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            nextQuestion(1)
        }

        prevButton.setOnClickListener {
            nextQuestion(9)
        }

    }

    fun checkAnswer(userAnswer: Boolean) {
        val currentQuestionAnswer = questionBank[currentIndex].answer
//        if(currentQuestionAnswer == userAnswer) {
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
//        }

        val messageId = if (currentQuestionAnswer == userAnswer){
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()

    }

    fun nextQuestion(index: Int) {
        currentIndex = (currentIndex + index) % questionBank.size
        displayedQuestion.setText(questionBank[currentIndex].questionTextId)
    }

}

