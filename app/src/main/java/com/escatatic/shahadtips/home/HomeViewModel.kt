package com.escatatic.shahadtips.home

import com.escatatic.shahadtips.base.BetStatus
import com.escatatic.shahadtips.data.model.request.UpdateScoreRequestBody
import com.escatatic.shahadtips.domain.repository.HomeRepository
import com.escatatic.shahadtips.home.uimodel.HomeViewEffect
import com.escatatic.shahadtips.home.uimodel.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: HomeRepository
): AndroidDataFlow(defaultState = HomeViewState()){

    init {
        fetchForTheFirstTime()
    }

    private fun fetchForTheFirstTime() = action(
        onAction = {
            sendEvent { HomeViewEffect.Loading }
            val result = repo.fetchMatchDates()
            setState { (it as HomeViewState).copy(dates = result) }
        },
        onError = { error, _ ->

        }
    )


}