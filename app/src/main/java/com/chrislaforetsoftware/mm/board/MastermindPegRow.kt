package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chrislaforetsoftware.mm.R

class MastermindPegRow(context: Context, attrs: AttributeSet?, defStyle: Int) : View(context, attrs, defStyle) {

	constructor(context: Context) : this(context, null, 0) {
//		init(null, 0)
	}

	constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {
//		init(attrs, 0)
	}


	private fun init(attrs: AttributeSet?, defStyle: Int) {
		// Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.MastermindPegHole, defStyle, 0
//        )

//        a.recycle()
	}

//	fun onCreate(savedInstanceState: Bundle) {
//		super.onCreate(savedInstanceState)
//		setContentView(R.layout.mastermind_pegrow);
//
//	}
}