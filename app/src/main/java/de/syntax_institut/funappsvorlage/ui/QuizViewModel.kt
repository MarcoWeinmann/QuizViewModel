package de.syntax_institut.funappsvorlage.ui

import androidx.lifecycle.ViewModel
import de.syntax_institut.funappsvorlage.data.QuizRepository
import de.syntax_institut.funappsvorlage.data.model.Question

/**
 * Das ViewModel kümmert sich um die Logik des Spiels.
 * Es ruft dabei >keine< Variablen oder Funktionen aus dem QuizFragment auf
 */
class QuizViewModel : ViewModel() {

    // Erstelle eine QuizRepository Instanz
    // TODO
    var repository = QuizRepository()

    // Lade die Liste mit den Question Informationen aus der QuizRepository Instanz
    // TODO
    var questionList = repository.loadQuestions()

    // Der Index zeigt die Position der aktuellen Frage in der Liste
    // TODO
    var questionIndex = 0

    // Diese Variable speichert die aktuelle Frage
    // TODO
    private var _currentQuestion = questionList[0]
    val currentQuestion: Question
        get() = _currentQuestion

    // Diese Variable speichert, wie viel Geld bereits erspielt wurde
    // TODO
    private var _moneyWon = 0
    val moneyWon: Int
        get() = _moneyWon

    // Diese Variable speichert, wie die letzte Frage beantwortet wurde
    // TODO
    private var _lastAnswer = true
    val lastAnswer: Boolean
        get() = _lastAnswer


    // Diese Variable speichert, ob die Millionenfrage beantwortet wurde
    // TODO
    private var _wonTheMillion = false
    val wonTheMillion: Boolean
        get() = _wonTheMillion


    /**
     * Diese Funktion startet das Spiel neu, indem alle Variablen zurückgesetzt werden
     */
    fun restartGame() {
        // TODO Schreibe hier deinen Code
        _moneyWon = 0
        questionIndex = 0
        _wonTheMillion = false
        _currentQuestion = questionList[0]
        _lastAnswer = true
    }

    /**
     * Diese Funktion überprüft, ob die Frage richtig oder falsch beantwortet wurde und setzt die
     * Variablen dementsprechend.
     */
    fun checkAnswer(answerIndex: Int) {
        // TODO Schreibe hier deinen Code
        if (answerIndex == _currentQuestion.rightAnswer) {
            _moneyWon = currentQuestion.price

            if (questionIndex == questionList.size -1){
                _wonTheMillion = true

            }else{
                questionIndex++
                _currentQuestion = questionList[questionIndex]
            }

        }else{
            _lastAnswer = false
        }
    }
}
