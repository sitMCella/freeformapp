package de.mcella.openapi.v3.freeformapp.user

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserController() {

    companion object {
        const val BASE_URL = "/api"
    }

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("$BASE_URL/user")
    fun create(
        @RequestParam queryParameters: MultiValueMap<String, String>
    ): ResponseEntity<User> {
        val user = User.convert(queryParameters)
        logger.info("Stored user: {}", user)
        val bodyBuilder = ResponseEntity.status(HttpStatus.CREATED)
        bodyBuilder.location(URI("$BASE_URL/user/${user.firstName}"))
        return bodyBuilder.body(user)
    }
}
