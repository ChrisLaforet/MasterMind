package com.chrislaforetsoftware.mm.rules

import com.chrislaforetsoftware.mm.exception.InvalidSizeException

data class Code(val colors: List<PegColor>) { }

fun Code.checkGuess(guessColors: List<PegColor>): Response {

    if (this.colors.size != guessColors.size) {
        throw InvalidSizeException()
    }

    var allCorrect = 0
    val unmatchedColors = mutableSetOf<PegColor>()
    val uniqueGuessColors = mutableSetOf<PegColor>()

    for (index in guessColors.indices) {
        val codeColor = colors[index]
        val guessColor = guessColors[index]
        if (codeColor == guessColor) {
            ++allCorrect
        } else {
            unmatchedColors.add(codeColor)
            uniqueGuessColors.add(guessColor)
        }
    }

    if (allCorrect == this.colors.size) {
        return Response(allCorrect, 0, true)
    }

    var colorCorrect = 0
    for (guessColor in uniqueGuessColors) {
        val sameColor: PegColor? = unmatchedColors.firstOrNull { it.color == guessColor.color }
        if (sameColor != null) {
            ++colorCorrect;
            unmatchedColors.remove(sameColor)
        }
    }

    return Response(allCorrect, colorCorrect)
}

