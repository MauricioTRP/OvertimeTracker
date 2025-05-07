package com.kotlinpl.auth.data

import android.util.Patterns
import com.kotlinpl.auth.domain.PatternValidator

class EmailPatternValidator : PatternValidator {
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}