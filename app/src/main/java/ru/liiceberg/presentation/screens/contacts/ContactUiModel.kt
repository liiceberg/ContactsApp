package ru.liiceberg.presentation.screens.contacts

data class ContactUiModel(
    val id: Long,
//    val firstLetter: Char,
    val name: String,
    val mobileNumber: String,
    val imageUrl: String,
)