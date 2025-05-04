package com.kotlinpl.domain

interface PatternValidator {
    fun matches(value: String) : Boolean
}