package com.creativijaya.profilebook.util.helper

import com.creativijaya.profilebook.MyAppConstant
import com.creativijaya.profilebook.util.ext.callOrNull
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object CurrencyHelper {

    private val locale by lazy {
        Locale("id", "ID")
    }

    fun cleanCurrency(currency: String?): BigDecimal {
        if (currency == null) return BigDecimal.ZERO

        if (currency.isEmpty() || currency.equals("null", ignoreCase = true)) BigDecimal.ZERO

        return callOrNull {
            DecimalFormat(MyAppConstant.CURRENCY_FORMAT, DecimalFormatSymbols(locale))
                .parse(currency)
                ?.toDouble()
                ?.toBigDecimal()
        } ?: BigDecimal.ZERO
    }

    fun formatCurrency(value: BigDecimal?): String {
        if (value == null) return ""

        return callOrNull {
            DecimalFormat(MyAppConstant.CURRENCY_FORMAT, DecimalFormatSymbols(locale))
                .format(value)
        } ?: ""
    }
}
