package com.wonative.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.wonative.app.screen.DiagnosisScreen

@Composable
fun App() {
    MaterialTheme {
        Surface {
            DiagnosisScreen()
        }
    }
}
