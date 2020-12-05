package de.mcella.openapi.v3.freeformapp.user

import de.mcella.openapi.v3.freeformapp.InvalidRequestException

@Suppress("DataClassPrivateConstructor")
data class User(
    val firstName: String,
    val lastName: String,
    val optionalFields: Map<String, String>,
    val optionalAddress: Address
) {

    companion object {

        const val FIRST_NAME_PARAMETER = "firstName"
        const val LAST_NAME_PARAMETER = "lastName"
        const val AGE_PARAMETER = "age"
        const val WEIGHT_PARAMETER = "weight"

        fun convert(parameters: Map<String, List<String>>): User {
            val firstName = getFirstName(parameters)
            val lastName = getLastName(parameters)
            val optionalUserFields = getOptionalUserFields(parameters)
            val optionalAddress = Address.convert(parameters)
            return User(firstName, lastName, optionalUserFields, optionalAddress)
        }

        private fun getFirstName(parameters: Map<String, List<String>>): String {
            return parameters[FIRST_NAME_PARAMETER]?.get(0) ?: throw InvalidRequestException("The query parameter \"$FIRST_NAME_PARAMETER\" is required")
        }

        private fun getLastName(parameters: Map<String, List<String>>): String {
            return parameters[LAST_NAME_PARAMETER]?.get(0) ?: throw InvalidRequestException("The query parameter \"$LAST_NAME_PARAMETER\" is required")
        }

        private fun getOptionalUserFields(parameters: Map<String, List<String>>): Map<String, String> {
            val optionalUserFields = mutableMapOf<String, String>()
            parameters[AGE_PARAMETER]?.get(0)?.let { optionalUserFields.put(AGE_PARAMETER, it) }
            parameters[WEIGHT_PARAMETER]?.get(0)?.let { optionalUserFields.put(WEIGHT_PARAMETER, it) }
            return optionalUserFields
        }
    }
}
