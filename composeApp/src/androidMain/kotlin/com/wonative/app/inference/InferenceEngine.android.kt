package com.wonative.app.inference

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.nio.FloatBuffer

actual class InferenceEngine {
    private var ortSession: OrtSession? = null
    private val ortEnv = OrtEnvironment.getEnvironment()

    actual fun loadModel(modelPath: String) {
        ortSession = ortEnv.createSession(modelPath)
    }

    actual fun runInference(imageBytes: ByteArray): InferenceOutput {
        val session = ortSession ?: return InferenceOutput("unknown", 0f)

        // 画像の前処理: 224x224 に resize して正規化 (TODO: 実モデルに合わせて調整)
        val inputTensor = preprocessImage(imageBytes)
        val inputName = session.inputNames.first()
        val results = session.run(mapOf(inputName to inputTensor))

        val output = results[0].value as Array<FloatArray>
        val scores = output[0]
        val maxIndex = scores.indices.maxByOrNull { scores[it] } ?: 0

        return InferenceOutput(
            label = LABELS.getOrElse(maxIndex) { "unknown" },
            confidence = scores[maxIndex]
        )
    }

    actual fun close() {
        ortSession?.close()
        ortEnv.close()
    }

    private fun preprocessImage(imageBytes: ByteArray): OnnxTensor {
        // TODO: 実際の前処理 (resize, normalize) を実装する
        val dummyInput = FloatBuffer.allocate(1 * 3 * 224 * 224)
        return OnnxTensor.createTensor(
            ortEnv,
            dummyInput,
            longArrayOf(1, 3, 224, 224)
        )
    }

    companion object {
        // TODO: 実際の病害ラベルに差し替える
        private val LABELS = listOf(
            "healthy", "early_blight", "late_blight", "leaf_mold", "unknown"
        )
    }
}
