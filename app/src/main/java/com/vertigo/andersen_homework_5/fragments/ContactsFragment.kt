package com.vertigo.andersen_homework_5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vertigo.andersen_homework_5.data.Contact
import com.vertigo.andersen_homework_5.data.ContactsData
import com.vertigo.andersen_homework_5.databinding.ContactsFragmentBinding
import com.vertigo.andersen_homework_5.interfaces.ContactsFragmentClickListener

class ContactsFragment: Fragment() {
    private lateinit var binding: ContactsFragmentBinding

    private val contactsFragmentPresenter = ContactsFragmentPresenter()
    private var contactsFragmentClickListener: ContactsFragmentClickListener? = null

    private lateinit var args: ContactsData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        args = arguments?.getSerializable("APP") as ContactsData
        contactsFragmentPresenter.attachView(this)
        contactsFragmentPresenter.setAllViews(args)

        return view
    }

    fun initTextViews(number: Int, contact: Contact) {
        with(binding) {
            contact1.setOnClickListener {
                contactsFragmentClickListener?.onContactClicked(1, args)
            }
            contact2.setOnClickListener {
                contactsFragmentClickListener?.onContactClicked(2, args)
            }
            contact3.setOnClickListener {
                contactsFragmentClickListener?.onContactClicked(3, args)
            }
            contact4.setOnClickListener {
                contactsFragmentClickListener?.onContactClicked(4, args)
            }
            contact5.setOnClickListener {
                contactsFragmentClickListener?.onContactClicked(5, args)
            }

            when (number) {
                1 -> {
                    nameContact1.text = contact.name
                    secondNameContact1.text = contact.secondName
                    phoneNumberContact1.text = contact.phoneNumber
                }
                2 -> {
                    nameContact2.text = contact.name
                    secondNameContact2.text = contact.secondName
                    phoneNumberContact2.text = contact.phoneNumber
                }
                3 -> {
                    nameContact3.text = contact.name
                    secondNameContact3.text = contact.secondName
                    phoneNumberContact3.text = contact.phoneNumber
                }
                4 -> {
                    nameContact4.text = contact.name
                    secondNameContact4.text = contact.secondName
                    phoneNumberContact4.text = contact.phoneNumber
                }
                5 -> {
                    nameContact5.text = contact.name
                    secondNameContact5.text = contact.secondName
                    phoneNumberContact5.text = contact.phoneNumber
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ContactsFragmentClickListener) {
            contactsFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        contactsFragmentClickListener = null
        contactsFragmentPresenter.detachView()
    }

    companion object {
        fun newInstance(contactsData: ContactsData): ContactsFragment {
            val args = Bundle()
            args.putSerializable("APP", contactsData)
            val fragment = ContactsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}