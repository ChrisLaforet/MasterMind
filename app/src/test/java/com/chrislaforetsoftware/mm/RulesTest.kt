package com.chrislaforetsoftware.mm

import com.chrislaforetsoftware.mm.rules.Code
import com.chrislaforetsoftware.mm.rules.Color
import com.chrislaforetsoftware.mm.rules.checkGuess
import org.junit.Assert.*
import org.junit.Test

class RulesTest {

    @Test
    fun givenACodeOfFour_whenScoringSameCode_thenReturnsFourBlack() {
        val colors = createCodeColorList(Color.Blue, Color.Blue, Color.Blue, Color.Blue)

        val code = Code(colors)
        val response = code.checkGuess(colors)

        assertEquals(4, response.matchCorrect)
    }

    @Test
    fun givenACodeOfFour_whenScoringSameCode_thenReturnsWin() {
        val colors = createCodeColorList(Color.Blue, Color.Blue, Color.Blue, Color.Blue)

        val code = Code(colors)
        val response = code.checkGuess(colors)

        assertTrue(response.win)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCode_thenReturnsNoWin() {
        val colors = createCodeColorList(Color.Blue, Color.Blue, Color.Blue, Color.Blue)
        val code = Code(colors)

        val guess = createCodeColorList(Color.Red, Color.Red, Color.Red, Color.Red)
        val response = code.checkGuess(guess)

        assertFalse(response.win)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCodeWithColorMatches_thenReturnsCountOfImperfectMatches() {
        val colors = createCodeColorList(Color.Blue, Color.Red, Color.Green, Color.Yellow)
        val code = Code(colors)

        val guess = colors.toMutableList()
        guess.reverse()
        val response = code.checkGuess(guess)

        assertEquals(4, response.colorCorrect)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCodeWithColorMatchesAndExactMatches_thenReturnsCountsOfEachMatch() {
        val colors = createCodeColorList(Color.Blue, Color.Red, Color.Green, Color.Yellow)
        val code = Code(colors)

        val guess = createCodeColorList(Color.Blue, Color.Yellow, Color.Green, Color.Red)
        val response = code.checkGuess(guess)

        assertEquals(2, response.colorCorrect)
        assertEquals(2, response.matchCorrect)
    }

    @Test
    fun givenACodeOfFourWithDuplicates_whenScoringUnmatchedCodeWithColorMatchesAndExactMatches_thenReturnsCountsOfEachMatch() {
        val colors = createCodeColorList(Color.Blue, Color.Red, Color.Blue, Color.Yellow)
        val code = Code(colors)

        val guess = createCodeColorList(Color.Blue, Color.Blue, Color.Green, Color.Yellow)
        val response = code.checkGuess(guess)

        assertEquals(1, response.colorCorrect)
        assertEquals(2, response.matchCorrect)
    }

    //-----------------

    private fun createCodeColorList(color0: Color, color1: Color, color2: Color, color3: Color): List<Color> {
        val colors = mutableListOf<Color>()
        colors.add(color0)
        colors.add(color1)
        colors.add(color2)
        colors.add(color3)
        return colors
    }


}