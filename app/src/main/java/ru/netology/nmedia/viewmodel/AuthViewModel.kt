package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import ru.netology.nmedia.auth.AppAuth
import ru.netology.nmedia.auth.AuthState
import javax.inject.Inject


@HiltViewModel
    class AuthViewModel @Inject constructor(private val auth: AppAuth, private val postViewModel: PostViewModel) : ViewModel() {
        val data: LiveData<AuthState> = auth.authStateFlow
            .asLiveData(Dispatchers.Default)

    fun refreshPostData(viewModelStoreOwner: ViewModelStoreOwner) {
        val postViewModel = ViewModelProvider(viewModelStoreOwner).get(PostViewModel::class.java)
        postViewModel.refreshData()
    }

    init {
        data.observeForever { authState ->
            if (authState.id != 0L) {
                postViewModel.refreshData()
            } else {
                postViewModel.refreshData()
            }
        }
    }

        val authenticated: Boolean
            get() = auth.authStateFlow.value.id != 0L

    }
