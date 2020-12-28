package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.chrislaforetsoftware.mm.R

class MastermindSelector(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle) {

    constructor(context: Context) : this(context, null, 0) {}

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    private lateinit var choice_red: Button
    private lateinit var choice_blue: Button
    private lateinit var choice_green: Button
    private lateinit var choice_yellow: Button
    private lateinit var choice_black: Button
    private lateinit var choice_white: Button
    private lateinit var choice_clear: Button

    init {
        inflate(this.context, R.layout.mastermind_selector, this)

        // https://stackoverflow.com/questions/16818146/android-popupwindow-above-a-specific-view
        choice_red = findViewById<Button>(R.id.choice_red)
        choice_blue = findViewById<Button>(R.id.choice_blue)
        choice_green = findViewById<Button>(R.id.choice_green)
        choice_yellow = findViewById<Button>(R.id.choice_yellow)
        choice_black = findViewById<Button>(R.id.choice_black)
        choice_white = findViewById<Button>(R.id.choice_white)
        choice_clear = findViewById<Button>(R.id.choice_clear)

        // https://developer.android.com/reference/android/widget/PopupWindow
        // https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android

    }
}