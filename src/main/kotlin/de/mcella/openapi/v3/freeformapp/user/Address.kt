package de.mcella.openapi.v3.freeformapp.user

data class Address(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postCode: String
) {
    companion object {

        private const val ADDRESS_HEADER_FIELD = "address."
        const val STREET_ADDRESS_PARAMETER = ADDRESS_HEADER_FIELD + "street"
        const val HOUSE_NUMBER_ADDRESS_PARAMETER = ADDRESS_HEADER_FIELD + "houseNumber"
        const val CITY_ADDRESS_PARAMETER = ADDRESS_HEADER_FIELD + "city"
        const val POST_CODE_ADDRESS_PARAMETER = ADDRESS_HEADER_FIELD + "postCode"

        fun convert(parameters: Map<String, List<String>>): Address {
            val street: String = parameters.getOrElse(STREET_ADDRESS_PARAMETER) { listOf("") }[0]
            val houseNumber: String = parameters.getOrElse(HOUSE_NUMBER_ADDRESS_PARAMETER) { listOf("") }[0]
            val city: String = parameters.getOrElse(CITY_ADDRESS_PARAMETER) { listOf("") }[0]
            val postCode: String = parameters.getOrElse(POST_CODE_ADDRESS_PARAMETER) { listOf("") }[0]
            return Address(street, houseNumber, city, postCode)
        }
    }
}
