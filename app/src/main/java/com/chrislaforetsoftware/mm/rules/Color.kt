package com.chrislaforetsoftware.mm.rules

data class Color(val color: String) {

    override fun toString(): String {
        return color
    }

    override fun equals(other: Any?): Boolean {
        if (other is Color) {
            return this.color == other.color
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return color.hashCode()
    }

    companion object {
        val Red = Color("Red")
        val Blue = Color("Blue")
        val Yellow = Color("Yellow")
        val Green = Color("Green")
        val White = Color("White")
        val Black = Color("Black")
    }
}