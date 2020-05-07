package com.amdev.lifehelper.planner.planslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.amdev.lifehelper.databases.plansdatabase.Plan
import com.amdev.lifehelper.databases.plansdatabase.PlansRepository
import kotlinx.coroutines.*

class PlansViewModel (
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var repository: PlansRepository = PlansRepository(application)
    private var allPlans: LiveData<List<Plan>> = repository.getAllPlansRep()

    suspend fun delete(plan: Plan) {
        withContext(Dispatchers.IO) {
            repository.delete(plan)
        }
    }

    fun onDelete(plan: Plan) {
        uiScope.launch {
            delete(plan)
        }
    }

    fun deleteAllPlans() {
        repository.deleteAllPLans()
    }

    fun getAllPlans(): LiveData<List<Plan>> {
        return allPlans
    }
}
