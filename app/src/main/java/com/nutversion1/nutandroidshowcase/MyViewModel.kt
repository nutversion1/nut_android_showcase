package com.nutversion1.nutandroidshowcase

import android.util.Log
import androidx.lifecycle.*
import com.nutversion1.nutandroidshowcase.api.ApiManager
import com.nutversion1.nutandroidshowcase.api.requests.DetectLanguageRequest
import com.nutversion1.nutandroidshowcase.api.requests.TranslateRequest
import com.nutversion1.nutandroidshowcase.api.responses.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    val randomQuote = MutableLiveData<GetRandomQuoteResponse>()

    val getRandomHobbyResponse = MutableLiveData<GetRandomHobbyResponse>()

    val getNumbersResponse = MutableLiveData<GetNumbersResponse>()

    val getMemesResponse = MutableLiveData<List<GetMemesResponse>>()

    val translateResponse = MutableLiveData<TranslateResponse>()
    val detectLanguageResponse = MutableLiveData<DetectLanguageResponse>()

    val fetchHoroscopeInformationResponse = MutableLiveData<FetchHoroscopeInformationResponse>()



    fun getRandomQuote(){
        viewModelScope.launch {
            val result = ApiManager.getRandomQuoteService().getRandomQuote()
            Log.d("myDebug", "result: ${result.body()}")

            randomQuote.postValue(result.body())
        }
    }

    fun getRandomHobby(category: String? = null){
        viewModelScope.launch {
            val result = ApiManager.getHobbiesService().getRandomHobby(category)
            Log.d("myDebug", "result: ${result.body()}")

            getRandomHobbyResponse.postValue(result.body())
        }
    }

    fun getRandomDateFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomDateFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomMathFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomMathFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomTriviaFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomTriviaFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getRandomYearFact(){
        viewModelScope.launch {
            val result = ApiManager.getNumbersService().getRandomYearFact()
            Log.d("myDebug", "result: ${result.body()}")

            getNumbersResponse.postValue(result.body())
        }
    }

    fun getMemes(){
        viewModelScope.launch {
            val result = ApiManager.getProgrammingMemesService().getMemes()
            Log.d("myDebug", "result: ${result.body()}")

            getMemesResponse.postValue(result.body())
        }
    }

    fun translate(translateRequest: TranslateRequest){
        viewModelScope.launch {
            val result = ApiManager.getTranslateService().translate(translateRequest)
            Log.d("myDebug", "result: $result ${result.body()}")

            translateResponse.postValue(result.body())
        }
    }

    fun detectLanguage(detectLanguageRequest: DetectLanguageRequest){
        viewModelScope.launch {
            val result = ApiManager.getTranslateService().detectLanguage(detectLanguageRequest)
            Log.d("myDebug", "result: $result ${result.body()}")

            detectLanguageResponse.postValue(result.body())
        }
    }

    fun fetchHoroscopeInformation(sign: String, day: String){
        viewModelScope.launch {
            val result = ApiManager.getAztroService().fetchHoroscopeInformation(sign, day)
            Log.d("myDebug", "result: $result ${result.body()}")

            fetchHoroscopeInformationResponse.postValue(result.body())
        }
    }





    class Factory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyViewModel() as T
        }
    }
}