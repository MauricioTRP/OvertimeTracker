package com.kotlinpl.core.domain.replacement

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Replacement(
    val unit: String,
    val startDate: Long,
    val endDate: Long
) {
    fun getStartDate() = convertMillisToDate(startDate)
    fun getEndDate() = convertMillisToDate(endDate)

    companion object {
        private fun convertMillisToDate(millis: Long) : String {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return formatter.format(Date(millis))
        }
    }
}