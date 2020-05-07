package com.amdev.lifehelper.databases.bdaysdatabase

import android.app.Application
import androidx.lifecycle.LiveData

class BdaysRepository (application: Application) {

    val database = BdaysDatabase.getInstance(application)
    val bdaysDao : BdaysDao = database.bdaysDao
    val allBdays : LiveData<List<Bday>> = bdaysDao.getAllBdays()

    fun insert(bday: Bday) {
        bdaysDao.insert(bday)
    }

    fun update(bday: Bday) {
        bdaysDao.update(bday)
    }

    fun delete(bday: Bday) {
        bdaysDao.delete(bday)
    }

    fun deleteAllBirthdays() {
        bdaysDao.deleteAllBdays()
    }

    fun getAllBirthdays(): LiveData<List<Bday>> {
        return allBdays
    }
}