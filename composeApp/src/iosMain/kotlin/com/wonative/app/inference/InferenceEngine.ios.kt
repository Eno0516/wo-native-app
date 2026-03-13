package com.wonative.app.inference

actual class InferenceEngine {
    actual fun loadModel(modelPath: String) {
        // TODO: Core ML (MLModel) で実装 (Mac 環境で実装)
        // .mlmodel ファイルを xcodeproj に追加してここで読み込む
    }

    actual fun runInference(imageBytes: ByteArray): InferenceOutput {
        // TODO: Core ML の prediction で実装 (Mac 環境で実装)
        return InferenceOutput(label = "unknown", confidence = 0f)
    }

    actual fun close() {
        // Core ML はリソース解放不要
    }
}
