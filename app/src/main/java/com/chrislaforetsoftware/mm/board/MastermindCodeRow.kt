package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.chrislaforetsoftware.mm.R
import com.chrislaforetsoftware.mm.rules.PegColor


class MastermindCodeRow(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0)
	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

	private var peg0: MastermindPegHole
	private var peg1: MastermindPegHole
	private var peg2: MastermindPegHole
	private var peg3: MastermindPegHole
	private var peg4: MastermindPegHole
	private var peg5: MastermindPegHole

	private var totalWells = PlayMastermindActivity.BASIC_WELLS
	private var activeWells: List<MastermindPegHole> = listOf()

	init {
		inflate(this.context, R.layout.mastermind_pegrow, this)

		peg0 = preparePegHole(R.id.peg_0)
		peg1 = preparePegHole(R.id.peg_1)
		peg2 = preparePegHole(R.id.peg_2)
		peg3 = preparePegHole(R.id.peg_3)
		peg4 = preparePegHole(R.id.peg_4)
		peg5 = preparePegHole(R.id.peg_5)
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
	}

	fun setCodeColors(pegs: List<PegColor>) {
		for (index in 0 until totalWells) {
			activeWells[index].setColor(pegs[index])
		}
	}
}