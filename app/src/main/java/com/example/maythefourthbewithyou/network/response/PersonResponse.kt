package com.example.maythefourthbewithyou.network.response

/*
data class for network response
 */
data class PersonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)