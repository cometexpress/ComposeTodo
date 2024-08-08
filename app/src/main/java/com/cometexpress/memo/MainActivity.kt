package com.cometexpress.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.cometexpress.memo.ui.theme.MemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {
            MemoTheme {
                Surface {
                    TodoListPage(viewModel)
                }
            }
        }
    }
}

@Composable
fun Test(title: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        color = Color.Magenta,
        shadowElevation = 5.dp,
        shape = CircleShape,
        border = BorderStroke(width = 1.dp, color = Color.Black)
    ) {
        Text(
            text = title,
            color = Color.Green,
            modifier = Modifier.padding(16.dp)
        )
    }
}