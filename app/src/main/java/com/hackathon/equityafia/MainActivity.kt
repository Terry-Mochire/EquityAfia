package com.hackathon.equityafia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.hackathon.equityafia.ui.theme.EquityAfiaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EquityAfiaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                },
                                navigationIcon = {
                                    IconToggleButton(checked = false, onCheckedChange = {}) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                            contentDescription = "Back"
                                        )
                                    }
                                },
                                actions = {
                                    IconToggleButton(checked = false, onCheckedChange = {}) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                                            contentDescription = "Back"
                                        )
                                    }
                                },
                            )
                        },
                        bottomBar = {
                            Text(text = "Bottom Bar")
                        }
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Hello World!")
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    EquityAfiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold (
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                        },
                        navigationIcon = {
                            IconToggleButton(checked = false, onCheckedChange = {}) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                    contentDescription = "Back"
                                )
                            }
                        },
                        actions = {
                            IconToggleButton(checked = false, onCheckedChange = {}) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                                    contentDescription = "Back"
                                )
                            }
                        },
                    )
                },
                bottomBar = {
                    Text(text = "Bottom Bar")
                }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Hello World!")
                }
            }
        }
    }
}