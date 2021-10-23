package com.alorma.compose.settings.example.extensions

import android.app.Activity
import android.content.Context
import androidx.navigation.NavController

fun NavController.popOrFinish(context: Context) {
    val result = popBackStack()
    if (!result && context is Activity) {
        context.finish()
    }
}