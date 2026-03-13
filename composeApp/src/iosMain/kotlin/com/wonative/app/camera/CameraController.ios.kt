package com.wonative.app.camera

actual class CameraController {
    actual fun startPreview() {
        // TODO: AVFoundation で実装 (Mac 環境で実装)
    }

    actual fun stopPreview() {
        // TODO: AVFoundation で実装 (Mac 環境で実装)
    }

    actual suspend fun captureImage(): ByteArray {
        // TODO: AVCaptureSession で実装 (Mac 環境で実装)
        return ByteArray(0)
    }
}
