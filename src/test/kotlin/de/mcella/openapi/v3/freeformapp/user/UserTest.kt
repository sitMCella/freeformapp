package de.mcella.openapi.v3.freeformapp.user

import de.mcella.openapi.v3.freeformapp.InvalidRequestException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UserTest {

    @Test
    fun `given none User query parameters, when converting the query parameters to User object, then the exception InvalidRequestException is thrown`() {
        Assertions.assertThrows(InvalidRequestException::class.java) {
            val queryParameters = mutableMapOf<String, List<String>>()

            User.convert(queryParameters)
        }
    }

    @Test
    fun `given the firstName and lastName User query parameters, when converting the query parameters to User object, then the User object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[User.FIRST_NAME_PARAMETER] = listOf("Henry")
        queryParameters[User.LAST_NAME_PARAMETER] = listOf("Lee")

        val user = User.convert(queryParameters)

        val expectedAddress = Address("","","","")
        val expectedUser = User("Henry", "Lee", emptyMap(), expectedAddress)
        assertEquals(expectedUser, user, "User object not matching")
    }

    @Test
    fun `given the firstName User query parameter, and without the lastName User query parameter, when converting the query parameters to User object, then the exception InvalidRequestException is thrown`() {
        Assertions.assertThrows(InvalidRequestException::class.java) {
            val queryParameters = mutableMapOf<String, List<String>>()
            queryParameters[User.FIRST_NAME_PARAMETER] = listOf("Henry")

            User.convert(queryParameters)
        }
    }

    @Test
    fun `given the lastName User query parameter, and without the firstName User query parameter, when converting the query parameters to User object, then the exception InvalidRequestException is thrown`() {
        Assertions.assertThrows(InvalidRequestException::class.java) {
            val queryParameters = mutableMapOf<String, List<String>>()
            queryParameters[User.LAST_NAME_PARAMETER] = listOf("Lee")

            User.convert(queryParameters)
        }
    }

    @Test
    fun `given the firstName, lastName, and the optional User query parameters, when converting the query parameters to User object, then the User object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[User.FIRST_NAME_PARAMETER] = listOf("Henry")
        queryParameters[User.LAST_NAME_PARAMETER] = listOf("Lee")
        queryParameters[User.AGE_PARAMETER] = listOf("28")
        queryParameters[User.WEIGHT_PARAMETER] = listOf("78")

        val user = User.convert(queryParameters)

        val expectedAddress = Address("","","","")
        val expectedUser = User("Henry", "Lee", mapOf(User.AGE_PARAMETER to "28", User.WEIGHT_PARAMETER to "78"), expectedAddress)
        assertEquals(expectedUser, user, "User object not matching")
    }

    @Test
    fun `given the firstName, lastName, the optional User query parameters, and the optional Address query parameters, when converting the query parameters to User object, then the User object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[User.FIRST_NAME_PARAMETER] = listOf("Henry")
        queryParameters[User.LAST_NAME_PARAMETER] = listOf("Lee")
        queryParameters[User.AGE_PARAMETER] = listOf("28")
        queryParameters[User.WEIGHT_PARAMETER] = listOf("78")
        queryParameters[Address.STREET_ADDRESS_PARAMETER] = listOf("Tincidunt Ave")
        queryParameters[Address.HOUSE_NUMBER_ADDRESS_PARAMETER] = listOf("50")
        queryParameters[Address.CITY_ADDRESS_PARAMETER] = listOf("Sedalia")
        queryParameters[Address.POST_CODE_ADDRESS_PARAMETER] = listOf("53700")

        val user = User.convert(queryParameters)

        val expectedAddress = Address("Tincidunt Ave","50","Sedalia","53700")
        val expectedUser = User("Henry", "Lee", mapOf(User.AGE_PARAMETER to "28", User.WEIGHT_PARAMETER to "78"), expectedAddress)
        assertEquals(expectedUser, user, "User object not matching")
    }
}
