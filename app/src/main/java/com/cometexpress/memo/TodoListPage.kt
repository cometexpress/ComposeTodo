package com.cometexpress.memo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(viewModel: TodoViewModel) {

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                label = {
                    Text(text = "할 일")
                }
            )

            Button(
                modifier = Modifier.padding(start = 8.dp),
                onClick = {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }
            ) {
                Text(
                    text = "추가",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }


        if (todoList.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    textAlign = TextAlign.Justify,
                    text = "할 일이 없습니다.",
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                content = {
                    itemsIndexed(todoList!!) { index: Int, item: Todo ->
                        TodoItem(
                            todo = item,
                            onDelete = {
                                viewModel.deleteTodo(item.id)
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(todo.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = todo.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(
            onClick = {
                onDelete()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_auto_delete),
                contentDescription = "제거",
                tint = Color.Red
            )
        }
    }
}