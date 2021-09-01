package com.vertigo.andersen_homework_5.fragments

import com.vertigo.andersen_homework_5.data.ContactsData

class ContactsFragmentPresenter {
    private var contactsFragment: ContactsFragment? = null

    fun setAllViews(contactsData: ContactsData) {
        for (el in contactsData.mapContacts) {
            contactsFragment?.initTextViews(el.key, el.value)
        }
    }


    fun attachView(view: ContactsFragment) {
        this.contactsFragment = view
    }

    fun detachView() {
        this.contactsFragment = null
    }
}