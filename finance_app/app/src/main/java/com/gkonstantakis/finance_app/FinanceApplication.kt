package com.gkonstantakis.finance_app

import android.app.Application
import androidx.room.Room
import com.gkonstantakis.finance_app.data.MainRepositoryImpl
import com.gkonstantakis.finance_app.data.database.FinanceDao
import com.gkonstantakis.finance_app.data.database.FinanceDatabase
import com.gkonstantakis.finance_app.data.mapping.FinanceInfoMapper
import com.gkonstantakis.finance_app.data.mapping.ProfitInfoMapper

class FinanceApplication : Application() {

    lateinit var financeDB: FinanceDatabase
    lateinit var financeDao: FinanceDao
    lateinit var mainRepository: MainRepositoryImpl

    override fun onCreate() {
        super.onCreate()

        financeDB = Room
            .databaseBuilder(
                applicationContext,
                FinanceDatabase::class.java,
                FinanceDatabase.FINANCE_DATABASE
            )
            .build()

        financeDao = (financeDB as FinanceDatabase).financeDao()

        mainRepository = MainRepositoryImpl(financeDao, FinanceInfoMapper(), ProfitInfoMapper())
    }
}