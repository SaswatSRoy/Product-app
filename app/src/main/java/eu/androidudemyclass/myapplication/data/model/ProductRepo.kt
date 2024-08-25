package eu.androidudemyclass.myapplication.data.model

import kotlinx.coroutines.flow.Flow

interface ProductRepo{
    suspend fun getProductsList():Flow<Result<List<Product>>>
}