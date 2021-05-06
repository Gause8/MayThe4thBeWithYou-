package com.example.maythefourthbewithyou.network.repository

import com.example.maythefourthbewithyou.network.Api

class StarWarsRepository {

    suspend fun getPeople() = Api().getPeople()

    suspend fun getNextPage(page: String) = Api().getNextPage(page)
}