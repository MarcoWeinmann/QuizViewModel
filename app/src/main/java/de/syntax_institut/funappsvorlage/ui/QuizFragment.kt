package de.syntax_institut.funappsvorlage.ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import de.syntax_institut.funappsvorlage.R
import de.syntax_institut.funappsvorlage.data.model.Question
import de.syntax_institut.funappsvorlage.databinding.FragmentQuizBinding

/**
 * Diese Klasse kümmert sich um die richtige Darstellung von UI Elementen.
 * Es ändert dabei >keine< Variablen im ViewModel
 */
class QuizFragment : Fragment() {

    // Hier wird das ViewModel, in dem die Logik stattfindet, geholt
    private val viewModel: QuizViewModel by viewModels()

    // Das binding für das QuizFragment wird deklariert
    private lateinit var binding: FragmentQuizBinding

    // Deklariert Variablen für die Hintergründe und Farben
    private lateinit var backgroundNormal: Drawable
    private lateinit var backgroundWrong: Drawable
    private lateinit var backgroundCorrect: Drawable
    private val white = Color.parseColor("#ffffff")
    private val black = Color.parseColor("#000000")

    /**
     * Lifecycle Funktion onCreateView
     * Hier wird das binding initialisiert und das Layout gebaut
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Lifecycle Funktion onViewCreated
     * Hier werden die Elemente eingerichtet und z.B. onClickListener gesetzt
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hole die Hintergründe aus der Ressource
        backgroundNormal = ContextCompat.getDrawable(requireContext(), R.drawable.background_text)!!
        backgroundWrong = ContextCompat.getDrawable(requireContext(), R.drawable.background_text_wrong)!!
        backgroundCorrect = ContextCompat.getDrawable(requireContext(), R.drawable.background_text_correct)!!

        // Setze Preisstufe, Frage und Antworten
        setPriceQuestionAnswer()

        // Setze onClickListeners
        binding.tvAnswerA.setOnClickListener {
            checkAnswerUpdateUI(binding.tvAnswerA, 1)
        }
        binding.tvAnswerB.setOnClickListener {
            checkAnswerUpdateUI(binding.tvAnswerB, 2)
        }
        binding.tvAnswerC.setOnClickListener {
            checkAnswerUpdateUI(binding.tvAnswerC, 3)
        }
        binding.tvAnswerD.setOnClickListener {
            checkAnswerUpdateUI(binding.tvAnswerD, 4)
        }
    }

    /**
     * Diese Funktion kümmert sich darum, in den entsprechenden Elementen die momentane Preisstufe,
     * Die momentane Frage und die momentanen Antwortmöglichkeiten anzuzeigen
     */
    private fun setPriceQuestionAnswer() {

        // Zeige die Preisstufe an
        // TODO
        binding.tvPrice.text = getString(R.string.current_price, viewModel.currentQuestion.price)

        // Zeige die Frage an
        // TODO
        binding.tvQuestion.text = viewModel.currentQuestion.question

        // Zeige die Antworten an
        // TODO
        binding.tvAnswerA.text= viewModel.currentQuestion.answerA
        binding.tvAnswerB.text= viewModel.currentQuestion.answerB
        binding.tvAnswerC.text= viewModel.currentQuestion.answerC
        binding.tvAnswerD.text= viewModel.currentQuestion.answerD


    }

    /**
     * Diese Funktion ruf die checkAnswer Funktion aus dem ViewModel auf und zeigt anschließend alle
     * betroffenen Elemente richtig an
     */
    private fun checkAnswerUpdateUI(textView: TextView, answerIndex: Int) {

        // Überprüfe, ob die Antwort stimmt
        // TODO
        viewModel.checkAnswer(answerIndex)


        // Wurde die Frage richtig beantwortet?
        // TODO
        if (viewModel.lastAnswer == true){
            textView.background = backgroundNormal
            textView.setTextColor(white)

            setPriceQuestionAnswer()
        }else{
            textView.background = backgroundWrong
            textView.setTextColor(black)
            showEndDialog(getString(R.string.game_over))
        }


        // Wenn das Spiel beendet werden soll (gewonnen)
        // TODO
        if (viewModel.wonTheMillion == true){
            textView.background = backgroundCorrect
            showEndDialog(getString(R.string.game_won))
        }


    }

    /**
     * Diese Funktion zeigt das Endergebnis in einem AlertDialog
     */
    private fun showEndDialog(title: String) {
        // TODO
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Gut geraten!")
            .setMessage("Du hast ${viewModel.moneyWon}")
            .setCancelable(false)
            .setNegativeButton(R.string.exit) { _, _->
                exitGame()
            }
            .setPositiveButton(R.string.play_again){ _, _->
                restartGame()
            }
            .show()

    }

    /**
     * Diese Funktion startet das Spiel neu, indem die restartGame Funktion des ViewModel aufgerufen
     * wird und die Farben und Texte entsprechend gesetzt werden
     */
    private fun restartGame() {
        viewModel.restartGame()

        // Setze Preisstufe, Frage und Antworten
        setPriceQuestionAnswer()

        // Setze die Farben zurück
        resetColors()
    }

    /**
     * Diese Funktion beendet das Spiel, indem die Activity beendet wird
     */
    private fun exitGame() {
        activity?.finish()
    }

    /**
     * Diese Funktion setzt die Farben zurück und stellt die ursprünglichen Hintergründe ein
     */
    private fun resetColors() {
        binding.tvAnswerA.background = backgroundNormal
        binding.tvAnswerA.setTextColor(white)
        binding.tvAnswerB.background = backgroundNormal
        binding.tvAnswerB.setTextColor(white)
        binding.tvAnswerC.background = backgroundNormal
        binding.tvAnswerC.setTextColor(white)
        binding.tvAnswerD.background = backgroundNormal
        binding.tvAnswerD.setTextColor(white)
    }
}
