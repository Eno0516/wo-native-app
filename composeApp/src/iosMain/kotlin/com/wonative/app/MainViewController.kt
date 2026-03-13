package com.wonative.app

import androidx.compose.ui.window.ComposeUIViewController
import com.wonative.app.di.appModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule)
        }
    }
) {
    App()
}
