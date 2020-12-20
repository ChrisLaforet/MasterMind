package com.chrislaforetsoftware.mm.rules

data class Response(val matchCorrect: Int, val colorCorrect: Int, val win: Boolean = false) {
}