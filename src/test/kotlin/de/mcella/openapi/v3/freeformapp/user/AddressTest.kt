package de.mcella.openapi.v3.freeformapp.user

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddressTest {

    @Test
    fun `given none optional Address query parameters, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = emptyMap<String, List<String>>()

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("","","","")
        assertEquals(expectedAddress, address, "Address object not matching")
    }

    @Test
    fun `given the street optional Address query parameter, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[Address.STREET_ADDRESS_PARAMETER] = listOf("Tincidunt Ave")

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("Tincidunt Ave","","","")
        assertEquals(expectedAddress, address, "Address object not matching")
    }

    @Test
    fun `given the house number optional Address query parameter, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[Address.HOUSE_NUMBER_ADDRESS_PARAMETER] = listOf("50")

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("","50","","")
        assertEquals(expectedAddress, address, "Address object not matching")
    }

    @Test
    fun `given the city optional Address query parameter, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[Address.CITY_ADDRESS_PARAMETER] = listOf("Sedalia")

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("","","Sedalia","")
        assertEquals(expectedAddress, address, "Address object not matching")
    }

    @Test
    fun `given the postCode optional Address query parameter, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[Address.POST_CODE_ADDRESS_PARAMETER] = listOf("53700")

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("","","","53700")
        assertEquals(expectedAddress, address, "Address object not matching")
    }

    @Test
    fun `given all the optional Address query parameters, when converting the query parameters to Address object, then the Address object is returned`() {
        val queryParameters = mutableMapOf<String, List<String>>()
        queryParameters[Address.STREET_ADDRESS_PARAMETER] = listOf("Tincidunt Ave")
        queryParameters[Address.HOUSE_NUMBER_ADDRESS_PARAMETER] = listOf("50")
        queryParameters[Address.CITY_ADDRESS_PARAMETER] = listOf("Sedalia")
        queryParameters[Address.POST_CODE_ADDRESS_PARAMETER] = listOf("53700")

        val address = Address.convert(queryParameters)

        val expectedAddress = Address("Tincidunt Ave","50","Sedalia","53700")
        assertEquals(expectedAddress, address, "Address object not matching")
    }
}
