package com.vertigo.andersen_homework_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val vi = findViewById<View>(R.id.second_container)
        if (vi != null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.second_container, DetailFragment.newInstance(number, contactsData))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, DetailFragment.newInstance(number, contactsData))
                .commit()
        }

    }

    override fun onButtonClicked() {
        val count: Int = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed();
        } else {
            supportFragmentManager.popBackStack()
            // Это делаем для того, чтобы спрятать фрагмент при сохранении или закрытии
            supportFragmentManager.beginTransaction().hide(DetailFragment()).commit()
            // Это для обновления данных на фрагменте со списком после редактирования
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, ContactsFragment.newInstance(contactsData))
                .commit()
        }
    }

    companion object {
        var contactsData = ContactsData()
    }
}