package com.vertigo.andersen_homework_5.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.vertigo.andersen_homework_5.data.Contact
import com.vertigo.andersen_homework_5.data.ContactsData
import com.vertigo.andersen_homework_5.databinding.DetailContactFragmentBinding
import com.vertigo.andersen_homework_5.interfaces.DetailFragmentClickListener

class DetailFragment: Fragment() {
    private lateinit var binding: DetailContactFragmentBinding

    private val detailFragmentPresenter = DetailFragmentPresenter()
    private var detailFragmentClickListener: DetailFragmentClickListener? = null
    private lateinit var args: ContactsData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailContactFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val number = arguments?.getInt("NUMBER")
        args = arguments?.getSerializable("OBJECT") as ContactsData

        detailFragmentPresenter.attachView(this)
        detailFragmentPresenter.setData(number, args)

        return view
    }


    fun setAllViews(contact: Contact?, id: Int?) {
        with(binding) {
            nameContact.setText(contact?.name, TextView.BufferType.EDITABLE)
            secondNameContact.setText(contact?.secondName, TextView.BufferType.EDITABLE)
            phoneNumberContact.setText(contact?.phoneNumber, TextView.BufferType.EDITABLE)

            editButton.setOnClickListener {
                detailFragmentPresenter.editContactData(id, args, nameContact.text.toString(),
                    secondNameContact.text.toString(), phoneNumberContact.text.toString())
                detailFragmentClickListener?.onButtonClicked()
            }

            cancelButton.setOnClickListener {
                detailFragmentClickListener?.onButtonClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailFragmentClickListener) {
            detailFragmentClickListener = context
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailFragmentPresenter.detachView()
        detailFragmentClickListener = null
    }

    companion object {
        fun newInstance(number: Int, contactsData: ContactsData): DetailFragment {
            val args = Bundle()
            args.putInt("NUMBER", number)
            args.putSerializable("OBJECT", contactsData)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}