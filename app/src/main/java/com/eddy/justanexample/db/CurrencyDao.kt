package com.eddy.justanexample.db

import androidx.room.*
import com.eddy.justanexample.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCurrency(currencyInfo: CurrencyInfo)

  @Update
  fun updateCurrency(currencyInfo: CurrencyInfo)

  @Delete
  fun deleteCurrency(currencyInfo: CurrencyInfo)

  @Query("DELETE FROM currencyInfo")
  fun deleteAllCurrency()

  @Query("SELECT * FROM currencyInfo ORDER BY ID ASC")
  fun getAllCurrency(): Flow<List<CurrencyInfo>>
}