package com.vertigo.andersen_homework_5.fragments

import android.util.Log
import com.vertigo.andersen_homework_5.data.Contact
import com.vertigo.andersen_homework_5.data.ContactsData

class DetailFragmentPresenter {
    private var detailFragment: DetailFragment? = null

    fun setData(number: Int?, contactsData: ContactsData) {
        val contact = contactsData.mapContacts[number]

        detailFragment?.setAllViews(contact, number)
    }

    fun editContactData(id: Int?, contactsData: ContactsData, name: String, secondName: String, phoneNumber: String) {
        Log.v("APP", "${ContactsData().mapContacts}")
        contactsData.setData(id!!, Contact(name, secondName, phoneNumber))
        Log.v("APP", "${ContactsData().mapContacts}")
    }

    fun attachView(view: DetailFragment) {
        this.detailFragment = view
    }

    fun detachView() {
        this.detailFragment = null
    }
}