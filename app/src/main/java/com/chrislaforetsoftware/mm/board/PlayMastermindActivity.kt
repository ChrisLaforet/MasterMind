package com.chrislaforetsoftware.mm.board

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.Code
import com.chrislaforetsoftware.mm.rules.PegColor
import com.chrislaforetsoftware.mm.rules.checkGuess
import java.util.*


class PlayMastermindActivity : Activity(), PegRowComplete {

    private var totalWells: Int = 4
    private var totalColors: Int = 6
    private var allowDuplicateColors: Boolean = false

    private lateinit var row0: MastermindPegRow
    private lateinit var row1: MastermindPegRow
    private lateinit var row2: MastermindPegRow
    private lateinit var row3: MastermindPegRow
    private lateinit var row4: MastermindPegRow
    private lateinit var row5: MastermindPegRow
    private lateinit var row6: MastermindPegRow
    private lateinit var row7: MastermindPegRow
    private lateinit var row8: MastermindPegRow
    private lateinit var row9: MastermindPegRow
    private lateinit var codeRow: MastermindCodeRow

    private lateinit var rows: List<MastermindPegRow>
    private var currentActiveRow: Int = 0

    private var codeToMatch: Code? = null

    companion object {
        const val TOTAL_WELLS = "totalWells"
        const val TOTAL_COLORS = "totalColors"
        const val ALLOW_DUPLICATE_COLORS = "allowDuplicateColors"

        const val MAX_WELLS = 6
        const val BASIC_WELLS = 4

        const val BASIC_COLORS = 6
        const val REDUCED_COLORS = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_play_mastermind)

        totalWells = intent.getIntExtra(TOTAL_WELLS, BASIC_WELLS)
        totalColors = intent.getIntExtra(TOTAL_COLORS, BASIC_COLORS)
        allowDuplicateColors = intent.getBooleanExtra(ALLOW_DUPLICATE_COLORS, false)

    }

    override fun onStart() {
        super.onStart()

        prepareRows()

        val code = generateCodeToMatch()
        val match = Code(code)
        codeToMatch = match
Log.d("CODE", "code is $match")
        codeRow.setCodeColors(match)

        // start the game clock?
        // Create clock here

        currentActiveRow = 0
        rows[currentActiveRow].activateRow(true)
    }

    private fun prepareRows() {
        val is6Well = totalWells == MAX_WELLS
        row0 = preparePegRow(R.id.peg_row_0, is6Well)
        row1 = preparePegRow(R.id.peg_row_1, is6Well)
        row2 = preparePegRow(R.id.peg_row_2, is6Well)
        row3 = preparePegRow(R.id.peg_row_3, is6Well)
        row4 = preparePegRow(R.id.peg_row_4, is6Well)
        row5 = preparePegRow(R.id.peg_row_5, is6Well)
        row6 = preparePegRow(R.id.peg_row_6, is6Well)
        row7 = preparePegRow(R.id.peg_row_7, is6Well)
        row8 = preparePegRow(R.id.peg_row_8, is6Well)
        row9 = preparePegRow(R.id.peg_row_9, is6Well)

        codeRow = prepareCodeRow(is6Well)

        val allRows = mutableListOf<MastermindPegRow>()
        allRows.add(row0)
        allRows.add(row1)
        allRows.add(row2)
        allRows.add(row3)
        allRows.add(row4)
        allRows.add(row5)
        allRows.add(row6)
        allRows.add(row7)
        allRows.add(row8)
        allRows.add(row9)

        rows = allRows.toList()
    }

    private fun preparePegRow(rowId: Int, is6Well: Boolean): MastermindPegRow {
        val row = findViewById<MastermindPegRow>(rowId)
        row.setWells(if (is6Well) MAX_WELLS else BASIC_WELLS)
        row.setChoices(if (totalColors == BASIC_COLORS) BASIC_COLORS else REDUCED_COLORS)
        row.registerCheckPlay(this)
        return row
    }

    private fun prepareCodeRow(is6Well: Boolean): MastermindCodeRow {
        val row = findViewById<MastermindCodeRow>(R.id.code_row)
        row.setWells(if (is6Well) MAX_WELLS else BASIC_WELLS)
        return row
    }

    private fun generateCodeToMatch(): List<PegColor> {
        val rng = Random()

        val haystack = createPegList()
        val code: MutableList<PegColor> = mutableListOf()
        for (index in 1..totalWells) {
            val offset = rng.nextInt(haystack.size)
            code.add(haystack[offset])
            if (!allowDuplicateColors) {
                haystack.removeAt(offset)
            }
        }
        return code.toList()
    }

    private fun createPegList(): MutableList<PegColor> {
        val list: MutableList<PegColor> = mutableListOf()
        list.add(PegColor.Red)
        list.add(PegColor.Blue)
        list.add(PegColor.Green)
        list.add(PegColor.Yellow)
        if (totalColors == BASIC_COLORS) {
            list.add(PegColor.Black)
            list.add(PegColor.White)
        }
        return list
    }

    override fun checkPlay(row: MastermindPegRow, pegs: List<PegColor>) {
        val code = checkNotNull(codeToMatch) { "Code cannot be null while checking play values" }

        val response = code.checkGuess(pegs)
        row.setResults(response)

        if (response.matchCorrect == totalWells) {
            showCode()
            showSuccessAlert()
            return
        }

        ++currentActiveRow
        if (currentActiveRow < rows.size) {
            rows[currentActiveRow].activateRow(true)
        } else {
            showCode()
            showFailureAlert()
        }
    }

    private fun showCode() {
        codeRow.displayCodeColors()
    }

    private fun showSuccessAlert() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.you_won)
        alertDialog.setMessage(R.string.won_prompt)
        alertDialog.setPositiveButton(R.string.yes) { _, _ -> this.recreate() }
        alertDialog.setNegativeButton(R.string.no) { _, _ -> this.finish() }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(true)
        alert.show()
    }

    private fun showFailureAlert() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.you_lost)
        alertDialog.setMessage(R.string.lost_prompt)
        alertDialog.setPositiveButton(R.string.yes) { _, _ -> this.recreate() }
        alertDialog.setNegativeButton(R.string.no) { _, _ -> this.finish() }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(true)
        alert.show()
    }
}