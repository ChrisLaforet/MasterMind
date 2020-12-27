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

    private var totalWells: Int = 4
    private var totalColors: Int = 6
    private var allowDuplicateColors: Boolean = false

    private lateinit var row0: MastermindPegRow
    private lateinit var row1: MastermindPegRow;
    private lateinit var row2: MastermindPegRow;
    private lateinit var row3: MastermindPegRow;
    private lateinit var row4: MastermindPegRow;
    private lateinit var row5: MastermindPegRow;
    private lateinit var row6: MastermindPegRow;
    private lateinit var row7: MastermindPegRow;
    private lateinit var row8: MastermindPegRow;
    private lateinit var row9: MastermindPegRow;

    companion object {
        const val TOTAL_WELLS = "totalWells"
        const val TOTAL_COLORS = "totalColors"
        const val ALLOW_DUPLICATE_COLORS = "allowDuplicateColors"

        const val MAX_WELLS = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)      // must precede content view

        setContentView(R.layout.activity_play_mastermind)

        totalWells = intent.getIntExtra(TOTAL_WELLS, 4)
        totalColors = intent.getIntExtra(TOTAL_COLORS, 6)
        allowDuplicateColors = intent.getBooleanExtra(ALLOW_DUPLICATE_COLORS, false)

        row0 = findViewById<MastermindPegRow>(R.id.peg_row_0)
        row0.setNumber(1)
        row1 = findViewById<MastermindPegRow>(R.id.peg_row_1)
        row1.setNumber(2)
        row2 = findViewById<MastermindPegRow>(R.id.peg_row_2)
        row2.setNumber(3)
        row3 = findViewById<MastermindPegRow>(R.id.peg_row_3)
        row3.setNumber(4)
        row4 = findViewById<MastermindPegRow>(R.id.peg_row_4)
        row4.setNumber(5)
        row5 = findViewById<MastermindPegRow>(R.id.peg_row_5)
        row5.setNumber(6)
        row6 = findViewById<MastermindPegRow>(R.id.peg_row_6)
        row6.setNumber(7)
        row7 = findViewById<MastermindPegRow>(R.id.peg_row_7)
        row7.setNumber(8)
        row8 = findViewById<MastermindPegRow>(R.id.peg_row_8)
        row8.setNumber(9)
        row9 = findViewById<MastermindPegRow>(R.id.peg_row_9)
        row9.setNumber(10)

        if (totalWells != MAX_WELLS) {
            row0.setWells(4)
            row1.setWells(4)
            row2.setWells(4)
            row3.setWells(4)
            row4.setWells(4)
            row5.setWells(4)
            row6.setWells(4)
            row7.setWells(4)
            row8.setWells(4)
            row9.setWells(4)
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }
 }