package com.amdev.lifehelper.bdays.bdayslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.amdev.lifehelper.databases.bdaysdatabase.Bday
import com.amdev.lifehelper.databases.bdaysdatabase.BdaysRepository
import kotlinx.coroutines.*

class BdaysViewModel (
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: BdaysRepository = BdaysRepository(application)
    private var allBdays: LiveData<List<Bday>> = repository.getAllBirthdays()

    suspend fun delete(bday: Bday) {
        withContext(Dispatchers.IO) {
            repository.delete(bday)
        }
    }

    fun onDelete(bday: Bday) {
        uiScope.launch {
            delete(bday)
        }
    }

    fun deleteAllBirthdays() {
        repository.deleteAllBirthdays()
    }

    fun getAllBirthdays(): LiveData<List<Bday>> {
        return allBdays
    }
}