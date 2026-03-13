package com.wonative.app.inference

/**
 * オンデバイス推論エンジンの共通インターフェース
 * Android: ONNX Runtime, iOS: Core ML で実装
 */
expect class InferenceEngine() {
    fun loadModel(modelPath: String)
    fun runInference(imageBytes: ByteArray): InferenceOutput
    fun close()
}

data class InferenceOutput(
    val label: String,
    val confidence: Float
)
