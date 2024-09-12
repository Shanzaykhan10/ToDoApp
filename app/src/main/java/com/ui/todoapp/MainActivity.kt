package com.ui.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToDoApp() {

    var showDialog by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val list = remember { mutableStateListOf<Data>() }
    var action by remember { mutableStateOf(value = false) }

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
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color(
                            0xFFDECBF1
                        )
                        ),
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
                Text(
                    text = "Manage Your Task",
                    modifier = Modifier.padding(20.dp), // Adjust padding as needed
                    fontSize = 30.sp, fontWeight = FontWeight.ExtraBold
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .padding(9.dp)
                            .height(60.dp)
                            .width(180.dp)
                            .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                            .background(
                                color = Color(0xFFC9AFE4)
                            )

                    )
                    {
                        Text(text = "In Progress", fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(60.dp)
                            .width(180.dp)
                            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
                            .background(
                                color = Color(0xFFD6D2D2)
                            )
                    )
                    {
                        Text(text = "Completed", fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.Black,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .padding(9.dp)
                            .height(60.dp)
                            .width(180.dp)
                            .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                            .background(
                                color = Color(0xff232323)
                            )

                    )
                    {
                        Text(text = "Cancel", fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.White,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(60.dp)
                            .width(180.dp)
                            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
                            .background(
                                color = Color(0xFFB9DA93)
                            )
                    )
                    {
                        Text(text = "Delayed", fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Enter Task") },
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
                        dismissButton = {
                            TextButton(onClick = {showDialog = false}) {
                                Text(text = "Cancel")
                            }
                        },

                        confirmButton = {
                            Row(horizontalArrangement = Arrangement.End) {
                                if (action.equals(false)) {
                                    Button(onClick = {
                                        if (id.isNotBlank() &&
                                            title.isNotBlank()&&
                                            description.isNotBlank())
                                        list.add(Data(id, title, description))
                                        showDialog = false
                                        id = ""
                                        title = ""
                                        description = ""

                                    }) {
                                        Text("Add")
                                    }
                                } else {
                                    TextButton(onClick = { /*TODO*/ }) {
                                        Text(text = "update")
                                    }
                                    Button(onClick = {}) {
                                         Text(text = "Delete")
                                    }
                                }
                            }
                        }
                    )
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color.Transparent)){
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        itemsIndexed(list) { index, data ->
                          Surface(onClick = {
                              action = true
                              showDialog = true
                              id = data.id
                              title = data.title
                              description = data.description
                          }) {
                              Row(modifier = Modifier.padding(top = 15.dp)) {
                                  Box(
                                      modifier = Modifier
                                          .background(
                                              if (index % 2 == 0) Color(0xFFC9AFE4)
                                              else Color(0xFFB9DA93)
                                          )
                                          .fillMaxWidth()
                                  )
                                  {
                                      Text(
                                          "ID: ${data.id}, Title: ${data.title}, " +
                                                  "Description: ${data.description}",
                                          fontSize = 20.sp,
                                          fontWeight = FontWeight.W600,
                                          modifier = Modifier.padding(15.dp)
                                      )
                                  }
                              }
                          }
                        }
                    }
                }
            }
        }
    )
}


