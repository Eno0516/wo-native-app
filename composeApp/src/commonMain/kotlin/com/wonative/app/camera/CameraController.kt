package com.wonative.app.camera

/**
 * カメラ操作の共通インターフェース
 * Android: CameraX, iOS: AVFoundation で実装
 */
expect class CameraController() {
    fun startPreview()
    fun stopPreview()
    suspend fun captureImage(): ByteArray
}
