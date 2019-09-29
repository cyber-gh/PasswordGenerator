package com.skyIT.passwordgenerator.gui.generic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.skyIT.passwordgenerator.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    private var completableJob = Job()
    protected var bacgroundScope = CoroutineScope(Dispatchers.IO + completableJob)
    override fun onCleared() {
        super.onCleared()
        completableJob.cancel()
    }

}