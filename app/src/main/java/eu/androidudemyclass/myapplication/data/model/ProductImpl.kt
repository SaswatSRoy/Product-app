package eu.androidudemyclass.myapplication.data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductImpl(
    private val api:Api
): ProductRepo {
    override suspend fun getProductsList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()
            }catch (e:IOException){
                e.printStackTrace()
                emit(Result.Error("Couldn't load data"))
                return@flow
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Result.Error("Couldn't load data"))
                return@flow
            }catch (e:Exception){
                e.printStackTrace()
                emit(Result.Error("Couldn't load data"))
                return@flow
            }
            emit(Result.Success(productsFromApi.products))
        }

    }

}