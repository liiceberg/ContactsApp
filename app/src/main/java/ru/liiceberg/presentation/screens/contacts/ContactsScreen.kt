package ru.liiceberg.presentation.screens.contacts

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.liiceberg.R
import ru.liiceberg.presentation.model.LoadState
import ru.liiceberg.presentation.ui.components.BaseAlertDialog
import ru.liiceberg.presentation.ui.components.BaseCard
import ru.liiceberg.presentation.ui.components.CircleAsyncImage
import ru.liiceberg.presentation.ui.components.ErrorMessage
import ru.liiceberg.presentation.ui.components.LoadingIndicator
import ru.liiceberg.presentation.utils.PermissionHandler
import ru.liiceberg.presentation.utils.PermissionResult
import ru.liiceberg.utils.callContact
import ru.liiceberg.utils.openAppSettings

@Composable
fun ContactsScreen(
    padding: PaddingValues,
    viewModel: ContactsViewModel = hiltViewModel(),
) {
    val state by viewModel.viewStates().collectAsStateWithLifecycle()

    var permissionState by remember { mutableStateOf<PermissionResult?>(null) }
    val context = LocalContext.current
    var launchRequest: (() -> Unit)? by remember { mutableStateOf(null) }

    PermissionHandler(
        permission = Manifest.permission.READ_CONTACTS,
        onPermissionLauncherReady = { launcher -> launchRequest = launcher },
        onResult = { result ->
            permissionState = result
        }
    )

    LaunchedEffect(permissionState) {
        if (permissionState == PermissionResult.Granted) {
            viewModel.obtainEvent(ContactsEvent.LoadContacts)
        }
    }

    Box(Modifier.padding(padding)) {
        when (permissionState) {
            PermissionResult.Granted -> {
                ContactsScreen(
                    state = state,
                    onContactClick = {
                        context.callContact(it)
                    }
                )
            }

            PermissionResult.ShowRationale -> {
                BaseAlertDialog(
                    stringResource(R.string.permission_needed),
                    stringResource(R.string.app_needs_contacts_permission_text)) {
                    launchRequest?.invoke()
                }
            }

            PermissionResult.PermanentlyDenied -> {
                ScreenWithoutPermission { context.openAppSettings() }
            }

            PermissionResult.RequestNeeded -> {
                LaunchedEffect(Unit) {
                    launchRequest?.invoke()
                }
            }

            else -> {}
        }
    }

}

@Composable
private fun ContactsScreen(
    state: ContactsState,
    onContactClick: (String) -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        with(state) {
            LazyColumn(Modifier.fillMaxWidth()) {
                state.contacts.entries.forEach { group ->

                    stickyHeader {
                        LetterHeader(letter = group.key)
                    }

                    item {
                        Spacer(Modifier.height(12.dp))
                    }

                    items(group.value.size) {
                        with(group.value[it]) {
                            ContactItem(
                                name,
                                mobileNumber,
                                imageUrl,
                                onClick = { onContactClick(mobileNumber) },
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }

                    item {
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }

            when (loadState) {
                is LoadState.Error -> ErrorMessage(
                    errorText = loadState.message,
                    modifier = Modifier.padding(16.dp)
                )
                LoadState.Loading -> LoadingIndicator(Modifier.align(Alignment.Center))
                else -> {}
            }
        }
    }
}

@Composable
private fun ScreenWithoutPermission(
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.no_contacts_permission_screen),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onOpenSettings) {
            Text(stringResource(R.string.open_settings_button))
        }
    }
}

@Composable
private fun LetterHeader(letter: Char, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = letter.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ContactItem(
    name: String,
    phone: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseCard(title = name, description = phone, modifier = modifier.clickable(onClick = onClick)) {
        CircleAsyncImage(
            url = imageUrl,
            error = painterResource(R.drawable.person),
            modifier = Modifier.height(48.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactsViewPreview() {
    ContactsScreen(
        ContactsState(
            contacts = mapOf(
                Pair(
                    'A',
                    listOf(
                        ContactUiModel(1, "Aisylu Gimaletdinova", "+7 987 777 77 77", ""),
                        ContactUiModel(2, "Aisylu Gimaletdinova", "+7 987 777 77 77", "")
                    )
                ),
                Pair('B', listOf(ContactUiModel(3, "Boris", "+7 987 777 77 77", ""))),
            ),
            loadState = LoadState.Initial
        )
    ) {}
}