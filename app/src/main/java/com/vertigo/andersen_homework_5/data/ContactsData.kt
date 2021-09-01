package com.vertigo.andersen_homework_5.data

import java.io.Serializable

class ContactsData: Serializable {

    var mapContacts = mutableMapOf(
        1 to Contact("Lady", "GaGa", "88005553535"),
        2 to Contact("Bob", "Marley", "112"),
        3 to Contact("Jared", "Leto", "87953544954"),
        4 to Contact("Matthew", "McConaughey", "8952435687"),
        5 to Contact("Colin", "Farrell", "794568756")
    )

    fun setData(id: Int, contact: Contact) {
        mapContacts.put(id, contact)
    }
}
