package com.wonative.app.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class CameraController {
    private var imageCapture: ImageCapture? = null

    actual fun startPreview() {
        // CameraX のプレビューは CameraPreviewView コンポーザブル側で管理
    }

    actual fun stopPreview() {
        // プレビュー停止は CameraProvider の unbind で行う
    }

    actual suspend fun captureImage(): ByteArray = suspendCancellableCoroutine { cont ->
        val capture = imageCapture ?: run {
            cont.resumeWithException(IllegalStateException("Camera not initialized"))
            return@suspendCancellableCoroutine
        }
        capture.takePicture(
            androidx.camera.core.ImageCapture.OutputFileOptions.Builder(
                java.io.ByteArrayOutputStream()
            ).build(),
            // TODO: Executor を注入する
            java.util.concurrent.Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    // TODO: ファイルから ByteArray を取得する
                    cont.resume(ByteArray(0))
                }
                override fun onError(exception: ImageCaptureException) {
                    cont.resumeWithException(exception)
                }
            }
        )
    }

    fun bindImageCapture(imageCapture: ImageCapture) {
        this.imageCapture = imageCapture
    }
}
