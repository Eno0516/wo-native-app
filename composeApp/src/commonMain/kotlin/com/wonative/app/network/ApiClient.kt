package com.wonative.app.network

import com.wonative.app.model.DiagnosisRequest
import com.wonative.app.model.DiagnosisResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiClient(private val baseUrl: String) {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun submitDiagnosis(request: DiagnosisRequest): DiagnosisResult {
        return client.post("$baseUrl/api/mobile/diagnose") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun getDiagnosisHistory(): List<DiagnosisResult> {
        return client.get("$baseUrl/api/mobile/diagnoses").body()
    }

    fun close() = client.close()
}
