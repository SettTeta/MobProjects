package vms.android.simplequiz

import android.util.Log
import androidx.lifecycle.ViewModel


class QuestionViewModel: ViewModel() {
    private val questionBank=listOf(Question(R.string.question_1,true, isAnswered = false),
        Question(R.string.question_2,true, isAnswered = false),
        Question(R.string.question_3,false, isAnswered = false),
        Question(R.string.question_4,false, isAnswered = false),
        Question(R.string.question_5,false, isAnswered = false),
        Question(R.string.question_6,true, isAnswered = false),
        Question(R.string.question_7,false, isAnswered = false),
        Question(R.string.question_8,false, isAnswered = false),
        Question(R.string.question_9,true, isAnswered = false),
        Question(R.string.question_10,false, isAnswered = false))


    private var currentIndex = 0
    private var answeredQuestion = 0
    private var correctAnswer = 0

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionIsAnswered: Boolean
        get() = questionBank[currentIndex].isAnswered

    val currentAnsweredQuestion: Int
        get() = answeredQuestion

    val currentCorrectAnswer: Int
        get() = correctAnswer

    val currentBankSize: Int
        get() = questionBank.size


    fun moveToNextQuestion(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevQuestion(){
        if (currentIndex == 0) {
            currentIndex = questionBank.size
        }
        currentIndex = (currentIndex - 1) % questionBank.size
    }

    fun answerQuestion(userAnswer: Boolean){
        answeredQuestion ++
        questionBank[currentIndex].isAnswered = true

        if(userAnswer == questionBank[currentIndex].answer){
            correctAnswer += 1
        }
    }

}

