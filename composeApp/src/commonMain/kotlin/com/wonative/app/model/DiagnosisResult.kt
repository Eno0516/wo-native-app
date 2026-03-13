package com.wonative.app.model

import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisResult(
    val id: String,
    val disease: String,
    val confidence: Float,
    val description: String,
    val recommendations: List<String>,
    val weatherContext: WeatherContext? = null,
    val capturedAt: String
)

@Serializable
data class WeatherContext(
    val temperature: Float,
    val humidity: Float,
    val condition: String
)

@Serializable
data class DiagnosisRequest(
    val imageBase64: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val localInferenceResult: String? = null  // オンデバイス推論の結果も送信
)
