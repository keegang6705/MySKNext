package com.colstu.mysknext.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            val googleAccount = GoogleSignIn.getLastSignedInAccount(this)

            val googleUsername = googleAccount?.displayName ?: "User"

            MainScreen(googleUsername = googleUsername, innerPadding = PaddingValues())
        }
    }
}

@Composable
fun MainScreen(googleUsername: String, innerPadding: PaddingValues) {
    Text(text = "Hello, $googleUsername")
}



