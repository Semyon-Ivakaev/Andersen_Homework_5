package com.vertigo.andersen_homework_5.interfaces

import com.vertigo.andersen_homework_5.data.ContactsData

interface ContactsFragmentClickListener {
    fun onContactClicked(number: Int, contactsData: ContactsData)
}