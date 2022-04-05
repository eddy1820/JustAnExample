package com.eddy.justanexample.repository

import com.eddy.justanexample.db.CurrencyDao
import com.eddy.justanexample.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val currencyDao: CurrencyDao) {

  fun getAllCurrency(): Flow<List<CurrencyInfo>> {
    return currencyDao.getAllCurrency()
  }
}