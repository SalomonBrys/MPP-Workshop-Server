package net.kodein.workshop.server

import net.kodein.workshop.server.data.Address
import net.kodein.workshop.server.data.Contact
import net.kodein.workshop.server.data.Name
import net.kodein.workshop.server.data.Phone
import org.dizitart.kno2.getRepository
import org.dizitart.no2.Nitrite
import java.util.*

fun Nitrite.init() {

    if (!hasRepository(Contact::class.java)) {
        getRepository<Contact> {
            insert(arrayOf(
                Contact(
                    UUID.randomUUID().toString(),
                    Name("Alain", "Dupont"),
                    listOf(
                        Address(
                            Address.Type.WORK,
                            "14-16 rue Voltaire",
                            "94270",
                            "Le Kremlin Bicêtre",
                            "FRANCE"
                        )
                    ),
                    listOf(
                        Phone(
                            Phone.Type.MOBILE,
                            "+33 6 19 82 37 54"
                        )
                    )
                ),
                Contact(
                    UUID.randomUUID().toString(),
                    Name("Jane", "Doe"),
                    listOf(
                        Address(
                            Address.Type.HOME,
                            "42 Some avenue",
                            "75012",
                            "Paris",
                            "FRANCE"
                        ),
                        Address(
                            Address.Type.WORK,
                            "14-16 rue Voltaire",
                            "94270",
                            "Le Kremlin Bicêtre",
                            "FRANCE"
                        )
                    ),
                    listOf(
                        Phone(
                            Phone.Type.HOME,
                            "+33 1 23 45 67 89"
                        ),
                        Phone(
                            Phone.Type.MOBILE,
                            "+33 6 15 27 38 49"
                        )
                    )
                ),
                Contact(
                    UUID.randomUUID().toString(),
                    Name("John", "Doe"),
                    listOf(
                        Address(
                            Address.Type.HOME,
                            "42 Some avenue",
                            "75012",
                            "Paris",
                            "FRANCE"
                        )
                    ),
                    listOf(
                        Phone(
                            Phone.Type.HOME,
                            "+33 1 23 45 67 89"
                        ),
                        Phone(
                            Phone.Type.MOBILE,
                            "+33 6 95 84 73 52"
                        )
                    )
                )
            ))
        }
    }

}