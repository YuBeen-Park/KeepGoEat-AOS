package org.keepgoeat.data.repository

import kotlinx.coroutines.flow.Flow
import org.keepgoeat.data.ApiResult
import org.keepgoeat.data.datasource.remote.GoalDataSource
import org.keepgoeat.data.model.request.RequestGoalAchievement
import org.keepgoeat.data.model.request.RequestGoalContent
import org.keepgoeat.data.model.request.RequestGoalContentTitle
import org.keepgoeat.data.model.response.*
import org.keepgoeat.domain.repository.GoalRepository
import timber.log.Timber
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalDataSource: GoalDataSource,
) : GoalRepository {
    override suspend fun fetchHomeEntireData(): Flow<ApiResult<ResponseHome.HomeData?>> =
        goalDataSource.fetchHomeEntireData()

    override suspend fun achieveGoal(
        goalId: Int,
        isAchieved: Boolean
    ): ResponseGoalAchievement.ResponseGoalAchievementData? {
        val result = goalDataSource.achievedGoal(goalId, RequestGoalAchievement(isAchieved))
        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }

    override suspend fun uploadGoalContent(
        title: String,
        isMore: Boolean
    ): ResponseGoalContent.ResponseGoalContentData? {
        val result = goalDataSource.uploadGoalContent(RequestGoalContent(title, isMore))

        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }

    override suspend fun editGoalContent(
        id: Int,
        title: String
    ): ResponseGoalContent.ResponseGoalContentData? {
        val result = goalDataSource.editGoalContent(id, RequestGoalContentTitle(title))

        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }

    override suspend fun fetchGoalDetail(goalId: Int): ResponseGoalDetail.ResponseGoalDetailData? {
        val result = goalDataSource.fetchGoalDetail(goalId)

        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }

    override suspend fun keepGoal(id: Int): ResponseGoalKeep.ResponseGoalKeepData? {
        val result = goalDataSource.keepGoal(id)

        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }

    override suspend fun deleteGoal(id: Int): ResponseGoalDeleted.ResponseGoalDeletedData? {
        val result = goalDataSource.deleteGoal(id)

        return when (result) {
            is ApiResult.Success -> {
                result.data?.data
            }
            is ApiResult.NetworkError -> {
                Timber.d("Network Error")
                null
            }
            is ApiResult.GenericError -> {
                Timber.d("(${result.code}): ${result.message}")
                null
            }
        }
    }
}
