package com.chrislaforetsoftware.mm.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.chrislaforetsoftware.mm.R

class MastermindPegHole : View {

    var colorName = "Empty"
    var pegColor = Color.GRAY

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

    fun setColor(colorName: String) {
        this.colorName = colorName
        pegColor = when (colorName) {
            "Red" -> Color.RED
            "Blue" -> Color.BLUE
            "Yellow" -> Color.YELLOW
            "Green" -> Color.GREEN
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            else -> Color.GRAY
        }

        invalidate()
    }

    fun getCodeColor() : com.chrislaforetsoftware.mm.rules.Color? {
        if (colorName.isEmpty()) {
            return null
        }
        return com.chrislaforetsoftware.mm.rules.Color(colorName)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.

  //      this.fore
        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom
    }
}