package com.chrislaforetsoftware.mm.rules

import com.chrislaforetsoftware.mm.exception.InvalidSizeException

data class Code(val colors: List<PegColor>) {
}

fun Code.checkGuess(guessColors: List<PegColor>): Response {

    if (this.colors.size != guessColors.size) {
        throw InvalidSizeException()
    }

    var allCorrect = 0
    val uniqueGuessColors = mutableSetOf<PegColor>()

    for (index in guessColors.indices) {
        val codeColor = colors[index]
        val guessColor = guessColors[index]
        if (codeColor == guessColor) {
            ++allCorrect
        } else {
            uniqueGuessColors.add(guessColor)
        }
    }

    if (allCorrect == this.colors.size) {
        return Response(allCorrect, 0, true)
    }

    val uniqueColors = mutableSetOf<PegColor>()
    for (color in this.colors) {
        uniqueColors.add(color)
    }
    var colorCorrect = 0
    for (guessColor in uniqueGuessColors) {
        if (uniqueColors.contains(guessColor)) {
            ++colorCorrect;
        }
    }

    return Response(allCorrect, colorCorrect)
}

