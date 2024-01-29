package com.example.peoples.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.peoples.repository.UserDataRepository

class UserDataViewModel : ViewModel() {
    private var userDataRepo: UserDataRepository = UserDataRepository()
    val fetchedUsers = userDataRepo.fetchUsers().cachedIn(viewModelScope)
}