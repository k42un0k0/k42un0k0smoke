package com.example.k42un0k0smoke.modules.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val firstName: LiveData<String>

    init{
        firstName = MutableLiveData<String>();
        firstName.value="hello";
    }
}