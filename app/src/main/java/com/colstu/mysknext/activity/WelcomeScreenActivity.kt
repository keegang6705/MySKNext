package com.colstu.mysknext.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.colstu.mysknext.ui.theme.MySKNextTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class WelcomeScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySKNextTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeScreen(LocalContext.current, innerPadding)
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(context: Context, innerPadding: PaddingValues) {
    val googleSignInClient =
        remember { GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN) }

    val account = GoogleSignIn.getLastSignedInAccount(context)
    val isSignedIn = account != null

    LaunchedEffect(isSignedIn) {
        if (isSignedIn) {
            context.startActivity(Intent(context, MainActivity::class.java))
        } else {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
    }
}




