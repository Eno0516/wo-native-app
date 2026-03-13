package com.wonative.app.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

actual class SpeechEngine {
    // Context は Application Context を想定 (Koin から注入)
    // TODO: Koin で Context を注入する形に変更する
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.JAPAN
            }
        }
    }

    actual fun speak(text: String, languageCode: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    actual fun stop() {
        tts?.stop()
    }

    actual fun shutdown() {
        tts?.shutdown()
        tts = null
    }
}
