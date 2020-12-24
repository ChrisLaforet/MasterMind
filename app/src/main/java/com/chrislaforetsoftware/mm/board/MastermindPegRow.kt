package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MastermindPegRow : View {
	constructor(context: Context) : super(context) {
		init(null, 0)
	}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		init(attrs, 0)
	}

	constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
			context,
			attrs,
			defStyle
	) {
		init(attrs, defStyle)
	}

	private fun init(attrs: AttributeSet?, defStyle: Int) {
		// Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.MastermindPegHole, defStyle, 0
//        )

//        a.recycle()
	}
}