package com.chrislaforetsoftware.mm

import com.chrislaforetsoftware.mm.rules.Code
import com.chrislaforetsoftware.mm.rules.PegColor
import com.chrislaforetsoftware.mm.rules.checkGuess
import org.junit.Assert.*
import org.junit.Test

class RulesTest {

    @Test
    fun givenACodeOfFour_whenScoringSameCode_thenReturnsFourBlack() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Blue, PegColor.Blue, PegColor.Blue)

        val code = Code(colors)
        val response = code.checkGuess(colors)

        assertEquals(4, response.matchCorrect)
    }

    @Test
    fun givenACodeOfFour_whenScoringSameCode_thenReturnsWin() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Blue, PegColor.Blue, PegColor.Blue)

        val code = Code(colors)
        val response = code.checkGuess(colors)

        assertTrue(response.win)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCode_thenReturnsNoWin() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Blue, PegColor.Blue, PegColor.Blue)
        val code = Code(colors)

        val guess = createCodeColorList(PegColor.Red, PegColor.Red, PegColor.Red, PegColor.Red)
        val response = code.checkGuess(guess)

        assertFalse(response.win)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCodeWithColorMatches_thenReturnsCountOfImperfectMatches() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Red, PegColor.Green, PegColor.Yellow)
        val code = Code(colors)

        val guess = colors.toMutableList()
        guess.reverse()
        val response = code.checkGuess(guess)

        assertEquals(4, response.colorCorrect)
    }

    @Test
    fun givenACodeOfFour_whenScoringUnmatchedCodeWithColorMatchesAndExactMatches_thenReturnsCountsOfEachMatch() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Red, PegColor.Green, PegColor.Yellow)
        val code = Code(colors)

        val guess = createCodeColorList(PegColor.Blue, PegColor.Yellow, PegColor.Green, PegColor.Red)
        val response = code.checkGuess(guess)

        assertEquals(2, response.colorCorrect)
        assertEquals(2, response.matchCorrect)
    }

    @Test
    fun givenACodeOfFourWithDuplicates_whenScoringUnmatchedCodeWithColorMatchesAndExactMatches_thenReturnsCountsOfEachMatch() {
        val colors = createCodeColorList(PegColor.Blue, PegColor.Red, PegColor.Blue, PegColor.Yellow)
        val code = Code(colors)

        val guess = createCodeColorList(PegColor.Blue, PegColor.Blue, PegColor.Green, PegColor.Yellow)
        val response = code.checkGuess(guess)

        assertEquals(1, response.colorCorrect)
        assertEquals(2, response.matchCorrect)
    }

    //-----------------

    private fun createCodeColorList(color0: PegColor, color1: PegColor, color2: PegColor, color3: PegColor): List<PegColor> {
        val colors = mutableListOf<PegColor>()
        colors.add(color0)
        colors.add(color1)
        colors.add(color2)
        colors.add(color3)
        return colors
    }


}