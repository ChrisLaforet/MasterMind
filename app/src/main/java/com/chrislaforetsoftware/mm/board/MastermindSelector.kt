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

class MastermindSelector(context: Context, choiceButton: MastermindPegHole, val listener: ChoiceListener) {

    private lateinit var choice_red: Button
    private lateinit var choice_blue: Button
    private lateinit var choice_green: Button
    private lateinit var choice_yellow: Button
    private lateinit var choice_black: Button
    private lateinit var choice_white: Button
    private lateinit var choice_clear: Button

    private lateinit var popup: PopupWindow

    init {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.mastermind_selector, null)

        choice_red = prepareChoiceButton(popupView, R.id.choice_red, PegColor.Red)
        choice_blue = prepareChoiceButton(popupView, R.id.choice_blue, PegColor.Blue)
        choice_green = prepareChoiceButton(popupView, R.id.choice_green, PegColor.Green)
        choice_yellow = prepareChoiceButton(popupView, R.id.choice_yellow, PegColor.Yellow)
        choice_black = prepareChoiceButton(popupView, R.id.choice_black, PegColor.Black)
        choice_white = prepareChoiceButton(popupView, R.id.choice_white, PegColor.White)
        choice_clear = prepareChoiceButton(popupView, R.id.choice_clear, PegColor.Clear)

        if (choiceButton.choices == 4) {
            choice_black.visibility = GONE
            choice_white.visibility = GONE
        }

        // https://developer.android.com/reference/android/widget/PopupWindow
        // https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
        // https://stackoverflow.com/questions/16818146/android-popupwindow-above-a-specific-view

        val unitSize = 2 * choiceButton.height / 3
        val focusable = true        // lets taps outside the popup also dismiss it
        popup = PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                focusable)
        popup.showAsDropDown(choiceButton, 0, -1 * (choiceButton.height + unitSize))
        popup.update(unitSize * (choiceButton.choices + 1), unitSize)
    }

    private fun prepareChoiceButton(popupView: View, choiceId: Int, peg: PegColor): Button {
        val button = popupView.findViewById<Button>(choiceId)
        button.setOnClickListener {
            this.listener.handleChoice(peg)
        }
        return button
    }

    fun close() {
        this.popup.dismiss()
    }
}


interface ChoiceListener {
    fun handleChoice(chosenPeg: PegColor)
}