package com.example.peoples.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.peoples.api.UsersApi
import com.example.peoples.helpers.Constants
import com.example.peoples.helpers.RetrofitHelper
import com.example.peoples.models.UserData
import com.example.peoples.models.UsersApiResponse
import com.example.peoples.paging.UserDataPagingSource

class UserDataRepository {
    private var usersApi: UsersApi = RetrofitHelper
                                    .getInstance()
                                    .create(UsersApi::class.java)

    fun fetchUsers():LiveData<PagingData<UserData>> {
        Log.d("tagy", "fetching users...")
        return Pager(
            config = PagingConfig(
                pageSize = Constants.USERS_FETCH_COUNT,
                maxSize = Constants.MAX_USERS_IN_MEMORY,
//                enablePlaceholders = false,
//                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                UserDataPagingSource(usersApi)
            }
            , initialKey = 1
        ).liveData
    }


}