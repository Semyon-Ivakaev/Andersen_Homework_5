package com.vertigo.andersen_homework_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vertigo.andersen_homework_5.data.ContactsData
import com.vertigo.andersen_homework_5.fragments.ContactsFragment
import com.vertigo.andersen_homework_5.fragments.DetailFragment
import com.vertigo.andersen_homework_5.interfaces.ContactsFragmentClickListener
import com.vertigo.andersen_homework_5.interfaces.DetailFragmentClickListener

class MainActivity : AppCompatActivity(), ContactsFragmentClickListener,
    DetailFragmentClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, ContactsFragment.newInstance(contactsData))
            .commit()
    }

    override fun onContactClicked(number: Int, contactsData: ContactsData) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, DetailFragment.newInstance(number, contactsData))
            .commit()
    }

    override fun onButtonClicked() {
        val count: Int = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed();
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    companion object {
        var contactsData = ContactsData()
    }
}