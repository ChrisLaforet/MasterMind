package com.chrislaforetsoftware.mm

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView


class HowToPlayMastermindActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.how_to_play_mastermind)

        val browser: WebView = findViewById<WebView>(R.id.how_to_play_webview)
        browser.settings.javaScriptEnabled = false

        try {
            val helpText = this.resources.openRawResource(R.raw.how_to_play_mastermind_en)
                .bufferedReader().use {
                    it.readText()
                }
            browser.loadData(helpText, "text/html; charset=utf-8", "UTF-8")
        } catch (ex: Exception) {
            browser.loadData("Cannot load how to text assets", "text/html; charset=utf-8", "UTF-8")
        }


//        browser.loadUrl("file:///android_asset/how_to_play_mastermind.en.html")
//
//        val helpText = requireContext().assets.open("how_to_play_mastermind.en.html").bufferedReader().use {
//        val helpText = this.applicationContext.assets.open("how_to_play_mastermind.en.html").bufferedReader().use {
//            it.readText()
//        }

    }
}