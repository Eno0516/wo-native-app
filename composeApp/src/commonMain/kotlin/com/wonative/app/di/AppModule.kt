package com.wonative.app.di

import com.wonative.app.camera.CameraController
import com.wonative.app.inference.InferenceEngine
import com.wonative.app.network.ApiClient
import com.wonative.app.tts.SpeechEngine
import org.koin.dsl.module

// TODO: 本番URLに変更する
private const val API_BASE_URL = "http://localhost:8080"

val appModule = module {
    single { ApiClient(API_BASE_URL) }
    factory { CameraController() }
    factory { InferenceEngine() }
    factory { SpeechEngine() }
}
