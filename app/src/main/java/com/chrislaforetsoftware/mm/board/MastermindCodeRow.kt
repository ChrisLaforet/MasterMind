package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.board.PlayMastermindActivity.Companion.BASIC_WELLS
import com.chrislaforetsoftware.mm.rules.Code
import com.chrislaforetsoftware.mm.rules.PegColor
import java.util.*


class MastermindCodeRow(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0)
	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

	private var pegs: LinearLayout
	private var peg0: MastermindPegHole
	private var peg1: MastermindPegHole
	private var peg2: MastermindPegHole
	private var peg3: MastermindPegHole
	private var peg4: MastermindPegHole
	private var peg5: MastermindPegHole

	private var hintButton: Button

	private var totalWells = PlayMastermindActivity.MAX_WELLS
	private var activeWells: List<MastermindPegHole> = listOf()

	private var hintMode = true;

	private var codeToMatch: Code? = null

	init {
		inflate(this.context, R.layout.mastermind_coderow, this)

		pegs = findViewById<LinearLayout>(R.id.pegs)
		peg0 = preparePegHole(R.id.peg_0)
		peg1 = preparePegHole(R.id.peg_1)
		peg2 = preparePegHole(R.id.peg_2)
		peg3 = preparePegHole(R.id.peg_3)
		peg4 = preparePegHole(R.id.peg_4)
		peg5 = preparePegHole(R.id.peg_5)

		hintButton = findViewById<Button>(R.id.hint_button)
		hintButton.setOnClickListener {
			showHint()
		}

		pegs.alpha = 0.4f
	}

	private fun preparePegHole(pegHoleId: Int): MastermindPegHole {
		return findViewById<MastermindPegHole>(pegHoleId)
	}

	fun setWells(wellCount: Int) {
		this.totalWells = wellCount

		val wells = mutableListOf<MastermindPegHole>()
		wells.add(peg0)
		wells.add(peg1)
		wells.add(peg2)
		wells.add(peg3)
		if (wellCount == PlayMastermindActivity.MAX_WELLS) {
			wells.add(peg4)
			wells.add(peg5)
		} else {
			peg4.visibility = GONE
			peg5.visibility = GONE
		}
		activeWells = wells.toList()
		setMaxWidthForPegs()
	}

	private fun showHint() {
		val clearWells = findIndexesForClearWells()
		if (clearWells.isEmpty()) {
			return
		}
		if (clearWells.size == 1) {
			showHintAt(clearWells[0])
		} else {
			val which = Random().nextInt(clearWells.size)
			showHintAt(clearWells[which])
		}
	}

	private fun findIndexesForClearWells(): List<Int> {
		val clearWells = mutableListOf<Int>()
		for (index in 0 until totalWells) {
			if (activeWells[index].getColor() == PegColor.Clear) {
				clearWells.add(index)
			}
		}
		return clearWells.toList()
	}

	private fun showHintAt(index: Int) {
		val code = codeToMatch ?: return
		activeWells[index].setColor(code.colors[index])
	}

	fun setCodeColors(code: Code) {
		codeToMatch = code
	}

	fun displayCodeColors() {
		val codeColors = codeToMatch ?: return
		for (index in 0 until totalWells) {
			activeWells[index].setColor(codeColors.colors[index])
		}

		pegs.alpha = 1.0f

		hintMode = false
		hintButton.visibility = GONE
	}

	private fun setMaxWidthForPegs() {
		val pegsWidth = context.resources.displayMetrics.widthPixels * 70 / 100
		val pegsHeight = context.resources.displayMetrics.heightPixels * 7 / 100
		pegs.layoutParams = LinearLayout.LayoutParams(pegsWidth, pegsHeight)

		val pegWidth = pegsWidth / PlayMastermindActivity.MAX_WELLS			// keep size consistent across the board
		for (well in this.activeWells) {
			well.maxPegWidth = pegWidth
		}
	}
}