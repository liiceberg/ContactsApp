package ru.liiceberg.data.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import ru.liiceberg.data.model.Contact
import ru.liiceberg.domain.repository.ContactsRepository
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver,
) : ContactsRepository {

    override fun getAll(): List<Contact> {
        val contacts = mutableListOf<Contact>()

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            "${ContactsContract.Contacts.DISPLAY_NAME} ASC"
        )
        cursor?.use {
            if (it.count > 0) {

                val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val hasPhoneIndex = it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                val photoIndex = it.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)

                while (it.moveToNext()) {

                    val contactId = cursor.getLong(idIndex)
                    val name = cursor.getString(nameIndex)
                    val photo = cursor.getString(photoIndex)
                    val hasPhone = it.getInt(hasPhoneIndex)

                    var phoneNumber: String? = null

                    if (hasPhone > 0) {

                        val phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                            arrayOf<String?>(contactId.toString()),
                            null
                        )
                        phoneCursor?.use {

                            val phoneIndex =
                                phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                            if (phoneCursor.moveToNext()) {
                                phoneNumber = phoneCursor.getString(phoneIndex)

                            }
                        }
                    }

                    contacts.add(
                        Contact(contactId, name, phoneNumber, photo)
                    )
                }
            }
        }

        return contacts
    }

}