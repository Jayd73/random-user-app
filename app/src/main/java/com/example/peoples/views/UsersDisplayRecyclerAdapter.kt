package com.example.peoples.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.peoples.databinding.UserDisplayItemBinding
import com.example.peoples.models.UserData
import com.squareup.picasso.Picasso
import kotlin.math.max

class UsersDisplayRecyclerAdapter
    : PagingDataAdapter<UserData, UsersDisplayRecyclerAdapter.UserDataViewHolder>(UserDataComparator) {

            private lateinit var dataBinding: UserDisplayItemBinding

        inner class UserDataViewHolder(val dataBinding: UserDisplayItemBinding): RecyclerView.ViewHolder(dataBinding.root)

    object UserDataComparator: DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return  oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): UsersDisplayRecyclerAdapter.UserDataViewHolder {
            dataBinding = UserDisplayItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
            return UserDataViewHolder(dataBinding)
        }

        override fun onBindViewHolder(
            holder: UsersDisplayRecyclerAdapter.UserDataViewHolder,
            position: Int
        ) {
            val user: UserData? = getItem(position)
            if (user != null) {
                holder.dataBinding.nameTextView.text =
                    convertToDisplayReadyText("${user.name.first} ${user.name.last}")
                holder.dataBinding.emailTextView.text = convertToDisplayReadyText("${user.email}")
                holder.dataBinding.ageTextView.text = "Age: ${user.dob.age}"
                holder.dataBinding.locationTextView.text =
                    convertToDisplayReadyText("${user.location.city}, ${user.location.country}")
                Picasso.get().load(user.picture.large).into(holder.dataBinding.userPhotoImgView)
            }
        }

        private fun convertToDisplayReadyText(text: String): String{
            val maxTextLen = 25
            if(text.length > maxTextLen){
                return text.take(maxTextLen) + "..."
            }
            return text
        }


}