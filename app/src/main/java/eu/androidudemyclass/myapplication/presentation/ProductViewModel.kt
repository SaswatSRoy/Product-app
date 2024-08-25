package eu.androidudemyclass.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.androidudemyclass.myapplication.data.model.Product
import eu.androidudemyclass.myapplication.data.model.ProductImpl
import eu.androidudemyclass.myapplication.data.model.ProductRepo
import eu.androidudemyclass.myapplication.data.model.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(

    private val productRepository: ProductRepo
):ViewModel() {

    private val _products= MutableStateFlow<List<Product>>(emptyList())
    val products=_products.asStateFlow()

    private val _showToastError=Channel<Boolean>()
    val showToastError = _showToastError.receiveAsFlow()

    init {
        viewModelScope.launch {
            productRepository.getProductsList().collectLatest{
                result ->
                when(result) {
                    is Result.Error -> {
                        _showToastError.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let {
                            products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }
}