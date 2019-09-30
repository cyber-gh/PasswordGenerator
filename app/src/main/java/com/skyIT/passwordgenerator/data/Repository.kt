package com.skyIT.passwordgenerator.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import javax.inject.Inject

class  Repository @Inject constructor(val productDao: PasswordDao) {
    val updatePasswordCacheList  = ConflatedBroadcastChannel<Unit>()
    val updatePasswordListLive = MutableLiveData<Boolean>().apply { value = false }
}