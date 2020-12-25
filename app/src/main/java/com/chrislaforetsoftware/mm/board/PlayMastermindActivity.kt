package com.chrislaforetsoftware.mm.board

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.chrislaforetsoftware.mm.R


class PlayMastermindActivity : Activity() {

    // ????
//private lateinit var fullscreenContent: TextView



    private var totalWells: Int = 4
    private var totalColors: Int = 6
    private var allowDuplicateColors: Boolean = false

    companion object {
        const val TOTAL_WELLS = "totalWells"
        const val TOTAL_COLORS = "totalColors"
        const val ALLOW_DUPLICATE_COLORS = "allowDuplicateColors"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)      // must precede content view

        setContentView(R.layout.activity_play_mastermind)


        totalWells = intent.getIntExtra(TOTAL_WELLS, 4)
        totalColors = intent.getIntExtra(TOTAL_COLORS, 6)
        allowDuplicateColors = intent.getBooleanExtra(ALLOW_DUPLICATE_COLORS, false)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }
 }