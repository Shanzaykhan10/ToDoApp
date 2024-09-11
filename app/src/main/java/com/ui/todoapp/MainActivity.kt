package com.ui.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoApp()
        }
    }
}

data class Data(val id: String, val title: String, val description: String)

@ExperimentalMaterial3Api
@Composable
fun ToDoApp() {
    var showDialog by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val dataList = remember { mutableStateListOf<Data>() }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.padding(16.dp),
                title = {},
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pic),
                        contentDescription = "My Pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                    )
                },
                actions = {
                    Spacer(modifier = Modifier.width(90.dp))

                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        colors = IconButtonDefaults.iconButtonColors(Color.Black)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = { },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.LightGray),
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                    ) {
                        Icon(
                            Icons.Outlined.Notifications,
                            contentDescription = "Settings Icon",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Enter Data") },
                        text = {
                            Column {
                                TextField(
                                    value = id,
                                    onValueChange = { id = it },
                                    label = { Text("ID") })
                                TextField(
                                    value = title,
                                    onValueChange = { title = it },
                                    label = { Text("Title") })
                                TextField(
                                    value = description,
                                    onValueChange = { description = it },
                                    label = { Text("Description") })
                            }
                        },
                        confirmButton = {
                            Row(horizontalArrangement = Arrangement.End) {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Cancel")
                                }
                                Button(onClick = {
                                    dataList.add(Data(id, title, description))
                                    showDialog = false
                                }) {
                                    Text("Add")
                                }
                            }
                        }
                    )
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    dataList.forEach { data ->
                        Text("ID: ${data.id}, Title: ${data.title}, " +
                                "Description: ${data.description}", fontSize = 20.sp, fontWeight = FontWeight.W600)
                    }
                }
            }
        }
    )
}





