package de.mcella.openapi.v3.freeformapp.user.integration

import de.mcella.openapi.v3.freeformapp.FreeformappApplication
import de.mcella.openapi.v3.freeformapp.user.Address
import de.mcella.openapi.v3.freeformapp.user.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URI
import java.net.URLEncoder
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [FreeformappApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `given a firstName, lastName, and optional User query parameters, when sending a POST REST request to the user endpoint, then the HTTP response 201 is returned`() {
        val queryParameters = mutableMapOf<String, String>()
        queryParameters[User.FIRST_NAME_PARAMETER] = "Henry"
        queryParameters[User.LAST_NAME_PARAMETER] = "Lee"
        queryParameters[User.AGE_PARAMETER] = "28"
        queryParameters[User.WEIGHT_PARAMETER] = "78"
        queryParameters[Address.STREET_ADDRESS_PARAMETER] = "Tincidunt Ave"
        queryParameters[Address.HOUSE_NUMBER_ADDRESS_PARAMETER] = "50"
        queryParameters[Address.CITY_ADDRESS_PARAMETER] = "Sedalia"
        queryParameters[Address.POST_CODE_ADDRESS_PARAMETER] = "53700"
        val queryParametersValue = queryParameters.toUrlQueryString()
        val request = HttpEntity("")

        val responseEntity = testRestTemplate.exchange(URI("http://localhost:$port/api/user?$queryParametersValue"), HttpMethod.POST, request, User::class.java)

        val expectedAddress = Address("Tincidunt Ave","50","Sedalia","53700")
        val expectedUser = User("Henry", "Lee", mapOf(User.AGE_PARAMETER to "28", User.WEIGHT_PARAMETER to "78"), expectedAddress)
        val expectedResponseEntity = ResponseEntity.status(HttpStatus.CREATED).body(expectedUser)
        assertEquals(expectedResponseEntity.statusCode, responseEntity.statusCode)
        assertEquals(expectedResponseEntity.body, (responseEntity as ResponseEntity<User>).body)
    }

    fun Map<String, String>.toUrlQueryString() =
        this.map {(k,v) -> "${URLEncoder.encode(k, "UTF-8")}=${URLEncoder.encode(v, "UTF-8")}" }
            .joinToString("&")
}
