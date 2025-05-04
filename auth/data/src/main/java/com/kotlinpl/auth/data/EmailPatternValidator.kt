package com.kotlinpl.auth.data

import android.util.Patterns
import com.kotlinpl.domain.PatternValidator

class EmailPatternValidator : PatternValidator {
    override fun matches(value: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}