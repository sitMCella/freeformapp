package de.mcella.openapi.v3.freeformapp.user

data class Address(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postCode: String
) {
    companion object {

        private const val ADDRESS_HEADER_FIELD = "address."

        fun convert(parameters: Map<String, List<String>>): Address {
            val street: String = parameters.getOrElse(ADDRESS_HEADER_FIELD + "street") { listOf("") }[0]
            val houseNumber: String = parameters.getOrElse(ADDRESS_HEADER_FIELD + "houseNumber") { listOf("") }[0]
            val city: String = parameters.getOrElse(ADDRESS_HEADER_FIELD + "city") { listOf("") }[0]
            val postCode: String = parameters.getOrElse(ADDRESS_HEADER_FIELD + "postCode") { listOf("") }[0]
            return Address(street, houseNumber, city, postCode)
        }
    }
}
