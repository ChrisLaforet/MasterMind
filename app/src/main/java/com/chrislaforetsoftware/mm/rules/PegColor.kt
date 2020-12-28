package com.chrislaforetsoftware.mm.rules

import android.graphics.Color

data class PegColor(val color: String) {

    override fun toString(): String {
        return color
    }

    override fun equals(other: Any?): Boolean {
        if (other is PegColor) {
            return this.color == other.color
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return color.hashCode()
    }

    fun getDisplayColor(): Int {
        return when (color) {
            "Red" -> Color.RED
            "Blue" -> Color.BLUE
            "Yellow" -> Color.YELLOW
            "Green" -> Color.GREEN
            "Black" -> Color.BLACK
            "White" -> Color.WHITE
            else -> Color.GRAY
        }
    }

    companion object {
        val Red = PegColor("Red")
        val Blue = PegColor("Blue")
        val Yellow = PegColor("Yellow")
        val Green = PegColor("Green")
        val White = PegColor("White")
        val Black = PegColor("Black")

        val Clear = PegColor("Clear")
    }
}