package com.chrislaforetsoftware.mm.rules

import com.chrislaforetsoftware.mm.exception.InvalidSizeException

data class Code(val colors: List<Color>) {
}

fun Code.checkGuess(guessColors: List<Color>): Response {

    if (this.colors.size != guessColors.size) {
        throw InvalidSizeException()
    }

    var allCorrect = 0
    val uniqueGuessColors = mutableSetOf<Color>()

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

    val uniqueColors = mutableSetOf<Color>()
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

