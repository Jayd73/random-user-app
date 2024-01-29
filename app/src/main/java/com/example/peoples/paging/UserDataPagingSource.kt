package com.example.peoples.paging

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.peoples.api.UsersApi
import com.example.peoples.helpers.Constants
import com.example.peoples.models.UserData

class UserDataPagingSource(private val usersApi: UsersApi): PagingSource<Int, UserData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val position = params.key ?: 1
            val response = usersApi.getUsers(position, Constants.USERS_FETCH_COUNT)
            LoadResult.Page(
                data = response.body()!!.userData,
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}