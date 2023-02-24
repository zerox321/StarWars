package com.star.wars.utility

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.*
import javax.inject.Inject

class TextToSpeechController @Inject constructor(context: Context) {
    private val tag = "TextToSpeechController"
    private val textToSpeech = TextToSpeech(context) { status ->
        Log.e(tag, "onInit: status $status")
    }.apply {
        val result = setLanguage(Locale.US)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) Log.e(
            tag, "The Language specified is not supported!"
        )
    }

    fun attachProgressListener(listener: UtteranceProgressListener) {
        textToSpeech.setOnUtteranceProgressListener(listener)
    }

    fun removeProgressListener() {
        textToSpeech.stop()
        textToSpeech.setOnUtteranceProgressListener(null)
    }

    fun speak(text: String?) {
        val speechStatus = textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "ID")
        if (speechStatus == TextToSpeech.ERROR) Log.e(tag, "Cant use the Text to speech.")
    }
}