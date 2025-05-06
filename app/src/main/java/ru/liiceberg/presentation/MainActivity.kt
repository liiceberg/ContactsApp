package ru.liiceberg.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import dagger.hilt.android.AndroidEntryPoint
import ru.liiceberg.presentation.screens.contacts.ContactsScreen
import ru.liiceberg.presentation.ui.theme.ContactsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsAppTheme {
                Scaffold { innerPadding ->
                    ContactsScreen(innerPadding)
                }
            }
        }
    }
}
