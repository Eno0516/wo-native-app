package com.wonative.app.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wonative.app.model.DiagnosisResult
import com.wonative.app.network.ApiClient
import com.wonative.app.model.DiagnosisRequest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun DiagnosisScreen() {
    val apiClient = koinInject<ApiClient>()
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<DiagnosisResult?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    error = null
                    try {
                        // TODO: CameraController から実際の画像を取得する
                        val dummyRequest = DiagnosisRequest(imageBase64 = "")
                        result = apiClient.submitDiagnosis(dummyRequest)
                    } catch (e: Exception) {
                        error = e.message
                    } finally {
                        isLoading = false
                    }
                }
            },
            enabled = !isLoading
        ) {
            Text("撮影して診断")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let {
            Text("エラー: $it")
        }

        result?.let { diagnosis ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("診断結果: ${diagnosis.disease}")
                    Text("確信度: ${(diagnosis.confidence * 100).toInt()}%")
                    Text(diagnosis.description)
                    diagnosis.recommendations.forEach { rec ->
                        Text("・$rec")
                    }
                }
            }
        }
    }
}
