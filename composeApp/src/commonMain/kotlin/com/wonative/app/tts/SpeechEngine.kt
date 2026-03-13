package com.wonative.app.tts

/**
 * 音声読み上げの共通インターフェース
 * Android: TextToSpeech API, iOS: AVSpeechSynthesizer で実装
 */
expect class SpeechEngine() {
    fun speak(text: String, languageCode: String = "ja-JP")
    fun stop()
    fun shutdown()
}
