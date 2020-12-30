package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor

class MastermindSelector(private val context: Context, private val activePegHole: MastermindPegHole, private val listener: ChoiceListener) {

    private lateinit var choiceRed: Button
    private lateinit var choiceBlue: Button
    private lateinit var choiceGreen: Button
    private lateinit var choiceYellow: Button
    private lateinit var choiceBlack: Button
    private lateinit var choiceWhite: Button
    private lateinit var choiceClear: Button

    private lateinit var popup: PopupWindow

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.mastermind_selector, null)

        choiceRed = prepareChoiceButton(popupView, R.id.choice_red, PegColor.Red)
        choiceBlue = prepareChoiceButton(popupView, R.id.choice_blue, PegColor.Blue)
        choiceGreen = prepareChoiceButton(popupView, R.id.choice_green, PegColor.Green)
        choiceYellow = prepareChoiceButton(popupView, R.id.choice_yellow, PegColor.Yellow)
        choiceBlack = prepareChoiceButton(popupView, R.id.choice_black, PegColor.Black)
        choiceWhite = prepareChoiceButton(popupView, R.id.choice_white, PegColor.White)
        choiceClear = prepareChoiceButton(popupView, R.id.choice_clear, PegColor.Clear)

        if (activePegHole.choices == 4) {
            choiceBlack.visibility = GONE
            choiceWhite.visibility = GONE
        }

        // https://developer.android.com/reference/android/widget/PopupWindow
        // https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
        // https://stackoverflow.com/questions/16818146/android-popupwindow-above-a-specific-view

        val unitSize = activePegHole.side
        val focusable = true        // lets taps outside the popup also dismiss it
        popup = PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                focusable)
        popup.showAsDropDown(activePegHole, 0, -1 * (activePegHole.height + unitSize))
        popup.update(unitSize * (activePegHole.choices + 1), unitSize)
    }

    private fun prepareChoiceButton(popupView: View, choiceId: Int, peg: PegColor): Button {
        val button = popupView.findViewById<Button>(choiceId)
        button.setOnClickListener {
            this.listener.handlePegColorChoice(this.activePegHole, peg)
        }
        return button
    }

    fun close() {
        this.popup.dismiss()
    }
}


interface ChoiceListener {
    fun handlePegColorChoice(activePegHole: MastermindPegHole, chosenPegColor: PegColor)
}