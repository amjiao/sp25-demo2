package com.example.demo2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoList() {
    var newTodo by remember { mutableStateOf("") }
    val todos = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 200.dp, bottom = 100.dp, start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("My Todo List", fontSize = 36.sp)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("new todo...") },
            value = newTodo,
            onValueChange = { newText ->
                newTodo = newText
            }
        )

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                todos.add(newTodo)
                newTodo = ""
            }
        ) {
            Text("Add Todo")
        }

        LazyColumn(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .height(300.dp)
                .padding(top = 20.dp)
        ) {
            items(todos.size) {
                TodoRow(todos[it], it)
            }
        }
    }
}

//maybe make this so that if you click on it it'll delete or move it to a separate list.
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoRow(todo: String, index: Int) {
    var bcolor = Color.LightGray
    if (index % 2 == 1) {
        bcolor = Color.White
    }

    // Get the current time as a number.
    val currentTimeMillis = System.currentTimeMillis()

    // Format it into a readable string.
    val formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")
        .withZone(ZoneId.systemDefault())
    val formattedTime = formatter.format(Instant.ofEpochMilli(currentTimeMillis))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = bcolor)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo,
                modifier = Modifier.weight(0.7f)
            )

            Text(
                text = formattedTime
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TodoListPreview() {
    TodoList()
}