package org.keepgoeat.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.keepgoeat.domain.model.GoalSticker
import javax.inject.Inject

@HiltViewModel
class GoalDetailViewModel @Inject constructor() : ViewModel() {
    private val _goalStickers = MutableLiveData<List<GoalSticker>>()
    val goalStickers: LiveData<List<GoalSticker>> get() = _goalStickers

    init {
        fetchGoalDetailInfo()
    }

    private fun fetchGoalDetailInfo() {
        // TODO api 연동 후 remote에서 데이터 불러오기
        val totalAchievementDays = 8
        _goalStickers.value = Array(CELL_COUNT) { idx ->
            GoalSticker(idx, idx < totalAchievementDays)
        }.toList()
    }

    companion object {
        const val CELL_COUNT = 35
    }
}
