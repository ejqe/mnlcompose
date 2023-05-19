package com.ejqe.mnlapp.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    placeholder: String,
){

    val keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    val isSingleLine = true

    BasicTextField(
        value = input.value,
        onValueChange = { input.value = it },
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        singleLine = isSingleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = {innerTextField ->
            Box {
                if (input.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}
