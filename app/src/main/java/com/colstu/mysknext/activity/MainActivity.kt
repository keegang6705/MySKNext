package com.colstu.mysknext.activity

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
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

            val googleIdToken = googleAccount?.idToken ?: ""

            MainScreen(innerPadding = PaddingValues(), token = googleIdToken)
        }
    }
}


@Composable
fun MainScreen(innerPadding: PaddingValues, token: String) {
    WebViewScreen(url = "https://keegang6705.lnw.mn/api/v1/login/index.php", token = token)
}

@Composable
fun WebViewScreen(url: String, token: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.setSupportMultipleWindows(true)
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.cacheMode = WebSettings.LOAD_DEFAULT
            settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"

            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            addJavascriptInterface(WebAppInterface(token), "Android")

            loadUrl(url)
        }
    }, modifier = Modifier.fillMaxSize())
}

class WebAppInterface(private val token: String) {
    @JavascriptInterface
    fun getToken(): String {
        return token
    }
}

