package ru.liiceberg.presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@Composable
fun BaseAlertDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }
    AlertDialog(
        onDismissRequest = {  openDialog.value = false },
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            Button(onClick = {
                onConfirm()
                openDialog.value = false
            }) {
                Text(stringResource(android.R.string.ok))
            }
        },
    )
}