package net.kodein.workshop.server.data

import org.dizitart.no2.objects.Id

data class Name(
    val firstName: String,
    val lastName: String
)

data class Contact(
    @Id val id: String,
    val name: Name,
    val addresses: List<Address>,
    val phones: List<Phone>
) {
    val fullName = "${name.lastName} ${name.firstName}"
}

data class Address(
    val type: Type,
    val street: String,
    val postalCode: String,
    val city: String,
    val country: String
) {
    enum class Type { HOME, WORK, OTHER }
}

data class Phone(
    val type: Type,
    val number: String
) {
    enum class Type { HOME, WORK, MOBILE, OTHER }
}
