package com.example.demo2.demo2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(todos){
                TodoRow(it)
            }
        }
    }
}

@Composable
fun TodoRow(todo: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(16.dp), //padding before vs after
    ) {
        Text(
            text = todo
        )

    }
}

@Preview
@Composable
fun TodoListPreview() {
    Column(){
        TodoList()
        TodoRow("This is a TodoRow")
    }
}