package de.mcella.openapi.v3.freeformapp.user

import de.mcella.openapi.v3.freeformapp.user.User.Companion.AGE_PARAMETER
import de.mcella.openapi.v3.freeformapp.user.User.Companion.FIRST_NAME_PARAMETER
import de.mcella.openapi.v3.freeformapp.user.User.Companion.LAST_NAME_PARAMETER
import de.mcella.openapi.v3.freeformapp.user.User.Companion.WEIGHT_PARAMETER
import de.mcella.openapi.v3.freeformapp.user.UserController.Companion.BASE_URL
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URLEncoder

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
@AutoConfigureWebClient
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `given a firstName, lastName, and optional User query parameters, when sending a POST REST request to the user endpoint, then the HTTP response 201 is returned`() {
        val queryParameters = mutableMapOf<String, String>()
        queryParameters[FIRST_NAME_PARAMETER] = "Henry"
        queryParameters[LAST_NAME_PARAMETER] = "Lee"
        queryParameters[AGE_PARAMETER] = "28"
        queryParameters[WEIGHT_PARAMETER] = "78"
        queryParameters[Address.STREET_ADDRESS_PARAMETER] = "Tincidunt Ave"
        queryParameters[Address.HOUSE_NUMBER_ADDRESS_PARAMETER] = "50"
        queryParameters[Address.CITY_ADDRESS_PARAMETER] = "Sedalia"
        queryParameters[Address.POST_CODE_ADDRESS_PARAMETER] = "53700"
        val queryParametersValue = queryParameters.toUrlQueryString()

        mockMvc.perform(
            MockMvcRequestBuilders.post("$BASE_URL/user?$queryParametersValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION))
        .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "$BASE_URL/user/Henry"))
    }

    @Test
    fun `given none User query parameters, when sending a POST REST request to the user endpoint, then the HTTP response 400 is returned`() {
        val queryParameters = mutableMapOf<String, String>()
        val queryParametersValue = queryParameters.toUrlQueryString()

        mockMvc.perform(
            MockMvcRequestBuilders.post("$BASE_URL/user?$queryParametersValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun `given a firstName User query parameter, and without the lastName User query parameter, when sending a POST REST request to the user endpoint, then the HTTP response 400 is returned`() {
        val queryParameters = mutableMapOf<String, String>()
        queryParameters[FIRST_NAME_PARAMETER] = "Henry"
        val queryParametersValue = queryParameters.toUrlQueryString()

        mockMvc.perform(
            MockMvcRequestBuilders.post("$BASE_URL/user?$queryParametersValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun `given a lastName User query parameter, and without the firstName User query parameter, when sending a POST REST request to the user endpoint, then the HTTP response 400 is returned`() {
        val queryParameters = mutableMapOf<String, String>()
        queryParameters[LAST_NAME_PARAMETER] = "Lee"
        val queryParametersValue = queryParameters.toUrlQueryString()

        mockMvc.perform(
            MockMvcRequestBuilders.post("$BASE_URL/user?$queryParametersValue")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    fun Map<String, String>.toUrlQueryString() =
        this.map {(k,v) -> "${URLEncoder.encode(k, "UTF-8")}=${URLEncoder.encode(v, "UTF-8")}" }
            .joinToString("&")
}
