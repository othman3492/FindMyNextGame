package com.othman.findmynextgame.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.othman.findmynextgame.data.repositories.GameRepository
import com.othman.findmynextgame.model.Game
import com.othman.findmynextgame.model.Result
import com.othman.findmynextgame.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class GameViewModel @ViewModelInject constructor(
    val repository: GameRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {


    val _gameList = MutableLiveData<Resource<List<Game>>>()
    val gameList: LiveData<Resource<List<Game>>> = _gameList

    init {

        getGames()
    }


    fun getGames() = viewModelScope.launch {

        _gameList.postValue(Resource.Loading())

        val response = repository.getGameList()
        _gameList.postValue(handleGameListResponse(response))
        }


    private fun handleGameListResponse(response: Response<Result>): Resource<List<Game>> {

        if (response.isSuccessful) response.body()?.let {
            return Resource.Success(it.results)
        }

        return Resource.Error(response.message(), null)
    }

}