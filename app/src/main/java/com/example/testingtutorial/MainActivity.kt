package com.example.testingtutorial

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testingtutorial.services.TodoService
import com.example.testingtutorial.ui.theme.TestingTutorialTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    var todoService: TodoService? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            todoService = (service as TodoService.TodoBinder).getService()
            Log.d("SERVICE", "Service Connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            todoService = null
            Log.d("SERVICE", "Service Disconnected")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM_TOKEN", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d("FCM_TOKEN", "YOUR DEVICE TOKEN: $token")
        }

        enableEdgeToEdge()

        setContent {
            TestingTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val notificationPermission = rememberPermissionState(
                            permission = Manifest.permission.POST_NOTIFICATIONS
                        ) { isGranted ->
                            Log.d("TDS_PER", "Notification permission granted: $isGranted")
                        }

                        LaunchedEffect(Unit) {
                            if (!notificationPermission.status.isGranted) {
                                notificationPermission.launchPermissionRequest()
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .systemBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "TodoScreen",
                            fontSize = 32.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, TodoService::class.java)

                            startService(intent)

                        }) {
                            Text("Start Service")
                        }

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, TodoService::class.java)
                            startForegroundService(intent)
                        }) {
                            Text("Start Sticky Notification")
                        }
                    }
                }
            }
        }
    }
}