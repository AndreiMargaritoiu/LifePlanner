package com.amdev.lifehelper.databases.plansdatabase

import android.app.Application
import androidx.lifecycle.LiveData

class PlansRepository (application: Application) {

    val database = PlansDatabase.getInstance(application)
    val plansDao : PlansDao = database.plansDao
    val allPlans : LiveData<List<Plan>> = plansDao.getAllPlans()

    fun insert(plan: Plan) {
        plansDao.insert(plan)
    }

    fun update(plan: Plan) {
        plansDao.update(plan)
    }

    fun delete(plan: Plan) {
        plansDao.delete(plan)
    }

    fun deleteAllPLans() {
        plansDao.deleteAllPlans()
    }

    fun getAllPlansRep(): LiveData<List<Plan>> {
        return allPlans
    }
}