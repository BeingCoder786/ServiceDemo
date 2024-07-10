package com.example.servicedemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.servicedemo.ui.theme.ServiceDemoTheme


import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.graphics.drawable.Animatable
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.os.BatteryManager
import android.os.Build

import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceDemoTheme {
                Surface(
                    // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    // on the below line we are specifying
                    // the theme as the scaffold.
                    Scaffold(

                        // in scaffold we are specifying the top bar.
                        topBar = {

                            // inside top bar we are specifying background color.
                            (
                                    TopAppBar(modifier = Modifier.background(Color.Red),

                                        // along with that we are specifying
                                        // title for our top bar.
                                        title = {

                                            // in the top bar we are specifying tile as a text
                                            Text(
                                                // on below line we are specifying
                                                // text to display in top app bar.
                                                text = "GFG",

                                                // on below line we are specifying
                                                // modifier to fill max width.
                                                modifier = Modifier.fillMaxWidth(),

                                                // on below line we are specifying
                                                // text alignment.
                                                textAlign = TextAlign.Center,

                                                // on below line we are specifying
                                                // color for our text.
                                                color = Color.White
                                            )
                                        })
                                    )
                        }) {
                        // on below line we are calling connection
                        // information method to display UI
                        ServicesUI(context = LocalContext.current)
                    }
                }


            }
        }
    }


    @Composable
    fun ServicesUI(context: Context) {
        // on below line creating variable
        // for service status and button value.
        val serviceStatus = remember {
            mutableStateOf(false)
        }
        val buttonValue = remember {
            mutableStateOf("Start Service")
        }

        // on below line we are creating a column,
        Column(
            // on below line we are adding a modifier to it,
            modifier = Modifier
                .fillMaxSize()
                // on below line we are adding a padding.
                .padding(all = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // on the below line we are adding a text for heading.
            Text(
                // on below line we are specifying text
                text = "Services in Android",
                // on below line we are specifying text color,
                // font size and font weight
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (serviceStatus.value) {
                    // service already running
                    // stop the service
                    serviceStatus.value = !serviceStatus.value
                    buttonValue.value = "Start Service"
                    context.stopService(Intent(context, MyService::class.java))

                } else {
                    // service not running start service.
                    serviceStatus.value = !serviceStatus.value
                    buttonValue.value = "Stop Service"

                    // starting the service
                    context.startService(Intent(context, MyService::class.java))
                }

            }) {
                // on below line creating a text for our button.
                Text(
                    // on below line adding a text,
                    // padding, color and font size.
                    text = buttonValue.value,
                    modifier = Modifier.padding(10.dp),
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

