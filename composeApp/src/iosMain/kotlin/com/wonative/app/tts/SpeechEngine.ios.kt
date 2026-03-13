package com.wonative.app.tts

import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance
import platform.AVFAudio.AVSpeechSynthesisVoice

actual class SpeechEngine {
    private val synthesizer = AVSpeechSynthesizer()

    actual fun speak(text: String, languageCode: String) {
        val utterance = AVSpeechUtterance(string = text)
        utterance.voice = AVSpeechSynthesisVoice.voiceWithLanguage(languageCode)
        utterance.rate = 0.5f
        synthesizer.speakUtterance(utterance)
    }

    actual fun stop() {
        synthesizer.stopSpeakingAtBoundary(platform.AVFAudio.AVSpeechBoundary.AVSpeechBoundaryImmediate)
    }

    actual fun shutdown() {
        stop()
    }
}
