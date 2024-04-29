package eu.radvan.papirfly.common

typealias JavaCurrency = java.util.Currency

enum class Currency {
    NOK,
    CZK,
    EUR,
    ;

    fun toJavaCurrency(): JavaCurrency = JavaCurrency.getInstance(this.name)

}