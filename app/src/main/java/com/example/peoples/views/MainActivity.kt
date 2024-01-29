package com.example.peoples.views

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peoples.R
import com.example.peoples.databinding.ActivityMainBinding
import com.example.peoples.viewModels.UserDataViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var userDataViewModel: UserDataViewModel
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userDataViewModel = ViewModelProvider(this).get(UserDataViewModel::class.java)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val usersDisplayAdapter = UsersDisplayRecyclerAdapter()

        val headerStateAdapter = LoaderAdapter()
        val footerStateAdapter = LoaderAdapter()

        usersDisplayAdapter.addLoadStateListener { combinedLoadStates ->
            headerStateAdapter.loadState = combinedLoadStates.refresh
            footerStateAdapter.loadState = combinedLoadStates.append
        }

        dataBinding.usersDisplayRecyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = ConcatAdapter(headerStateAdapter, usersDisplayAdapter, footerStateAdapter)
        }

        userDataViewModel.fetchedUsers.observe(this){ pagingData ->
            usersDisplayAdapter.submitData(lifecycle, pagingData)
        }

    }
}