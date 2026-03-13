package com.example.testingtutorial.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp


@Composable
fun CalcScreen(viewModel: CalcViewModel) {

    var a by rememberSaveable { mutableStateOf(0)}
    var b by rememberSaveable { mutableStateOf(0)}

    var result by rememberSaveable { mutableStateOf(0)}

    Column(modifier = Modifier.fillMaxSize()) {
        InField(
            modifier = Modifier.testTag("FIELD_A"),
            label = "Field A",
            value = a,
            onValueChange = {a = it}
        )
        Spacer(Modifier.height(24.dp))
        InField(
            modifier = Modifier.testTag("FIELD_B"),
            label = "Field B",
            value = b,
            onValueChange = {b = it}
        )
        Spacer(Modifier.height(24.dp))

        Text("Result: $result ")

        Button(
            modifier = Modifier.testTag("add_Btn"),
            onClick = {
                result = viewModel.add(a,b)
            }
        ) {
            Text("Add the fields")
        }

        Text("Subtract result: $result ")

        Button(
            modifier = Modifier.testTag("add_Btn"),
            onClick = {
                result = viewModel.subtract(a,b)
            }
        ) {
            Text("Subtract the fields")
        }
    }
}

@Composable
fun InField(modifier: Modifier, label: String, value: Int, onValueChange: (Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        TextField(
            value="",
            onValueChange = { newValue ->
                onValueChange(newValue.toInt())
            },
            label={
                Text(label)
        })
    }
}

