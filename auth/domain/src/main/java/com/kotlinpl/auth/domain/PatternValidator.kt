package com.kotlinpl.auth.domain

interface PatternValidator {
    fun matches(value: String) : Boolean
}