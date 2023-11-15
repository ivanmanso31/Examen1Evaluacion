package com.example.sumador.ui.theme


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun SumadorApp(){
    val viewModel: SumadorViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isShowingFirstPage) {
        SumadorPrincipal(
            viewModel,
            modifier = Modifier
        )
    } else {
        SumadorSecundario(
            viewModel,
            uiState.sumador1,
            uiState.sumador2,
            modifier = Modifier
        )
    }
    SumadorPrincipal(viewModel)
}
@Composable
private fun SumadorPrincipal(viewModel: SumadorViewModel, modifier: Modifier = Modifier){
    var sumador1Principal by remember { mutableStateOf("") }
    var sumador2Principal by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ){
        Spacer(Modifier.weight(1f))
        Sumador(keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
            value = sumador1Principal,
            onValueChanged = { sumador1Principal = it })
        Spacer(Modifier.weight(1f))
        Sumador(keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
            value = sumador2Principal,
            onValueChanged = { sumador2Principal = it })
        Spacer(Modifier.weight(1f))
        Button(onClick = { viewModel.updateSumadores(sumador1Principal.toInt(), sumador2Principal.toInt()) }) {
            Text("+")
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sumador(
             keyboardOptions: KeyboardOptions,
             value: String,
             onValueChanged: (String) -> Unit,
             modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions
    )
}

@Composable
private fun SumadorSecundario(
    viewModel: SumadorViewModel,
    sum1: Int,
    sum2: Int,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()){
        Spacer(Modifier.weight(1f))
        Text(
            text = "$sum1 + $sum2 = ${sum1 + sum2}"
        )
        Spacer(Modifier.weight(1f))
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()){
        }
        Button(onClick = { viewModel.navigateToFirstPage() }) {
            Text("volver")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SumadorLayoutPreview() {
        SumadorApp()
}