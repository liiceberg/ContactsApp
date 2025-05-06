package ru.liiceberg.presentation.utils

sealed class PermissionResult {
    data object Granted : PermissionResult()
    data object ShowRationale : PermissionResult()
    data object PermanentlyDenied : PermissionResult()
    data object RequestNeeded : PermissionResult()
}
