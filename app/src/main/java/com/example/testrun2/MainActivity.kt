package com.example.testrun2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.testrun2.ui.theme.Testrun2Theme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
//TODO: DEVELOPMENT BRANCH
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxWidth()) {

                val itemsTheUserAdded:List<String> = textInput()
                Spacer(modifier = Modifier.weight(1f))
                ItemsList(itemsTheUserAdded)
            }
        }
    }
}

@Composable
fun ItemsList(updatedItemsTheUserAdded:List<String>) {
    var itemsTheUserAdded:List<String> by remember {
        mutableStateOf(listOf())
    }
    itemsTheUserAdded = itemsTheUserAdded + updatedItemsTheUserAdded
    Column(modifier = Modifier
        .fillMaxSize()
    ){
        LazyColumn{
            items(itemsTheUserAdded.reversed()){ currentItemInTheList ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = currentItemInTheList,
                        modifier = Modifier
                            .align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    ){
                        Button(
                            onClick = {
                                      itemsTheUserAdded = itemsTheUserAdded - currentItemInTheList
                                      },
                            colors = ButtonDefaults.buttonColors(
                                Color(255,125,125)
                            )
                        ) {
                            Text(text = "-")
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textInput():List<String> {
    var input:String by remember {
        mutableStateOf("")
    }
    var tempInput:String by remember {
        mutableStateOf("")
    }
    val itemsTheUserAdded:List<String> by remember {
        mutableStateOf(listOf())
    }
    var hasUserClickedTheAddButton by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(40.dp)
    ) {
        TextField(
            value = input,
            onValueChange = {textTheUserIsTyping:String ->
                input = textTheUserIsTyping
            },
            modifier = Modifier
                //.fillMaxWidth()
                .weight(1f)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)

        ){
            Button(
                onClick = {
                    if(input.isEmpty()) return@Button
                    hasUserClickedTheAddButton = true
                    tempInput = input
                    input = ""
                },
                colors = ButtonDefaults.buttonColors(
                    Color(125,255,125)
                )
            ) {
                Text(text = "+")
            }
        }
    }
    if (hasUserClickedTheAddButton) {
        hasUserClickedTheAddButton = false
        return itemsTheUserAdded + tempInput
    }
    return itemsTheUserAdded
}

@Preview
@Composable
fun TextInputPreview() {
    val itemsTheUserAdded:List<String> = textInput()
    ItemsList(itemsTheUserAdded)
}