package com.amdev.lifehelper.utilsubs.utilsubslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.amdev.lifehelper.databases.utilsubsdatabase.Utilsub
import com.amdev.lifehelper.databases.utilsubsdatabase.UtilsubsRepository
import kotlinx.coroutines.*

class UtilsubsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: UtilsubsRepository = UtilsubsRepository(application)
    private var allUtilsubs: LiveData<List<Utilsub>> = repository.getAllutilsubs()

    suspend fun delete(utilsub: Utilsub) {
        withContext(Dispatchers.IO) {
            repository.delete(utilsub)
        }
    }

    fun onDelete(utilsub: Utilsub) {
        uiScope.launch {
            delete(utilsub)
        }
    }

    fun deleteAllUtilsubs() {
        repository.deleteAllUtilsubs()
    }

    fun getAllutilsubs(): LiveData<List<Utilsub>> {
        return allUtilsubs
    }

}
