package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.chrislaforetsoftware.mm.R


class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : LinearLayout(context, attrs, defStyle) {
//	class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : View(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0) {}

	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

	init {
//		val viewGroup = findViewById<View>(android.R.id.content) as ViewGroup

//		val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//		var view = inflater.inflate(R.layout.mastermind_pegrow, null)

		inflate(this.context, R.layout.mastermind_pegrow, this)
	}
}