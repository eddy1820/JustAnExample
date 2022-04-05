package com.eddy.justanexample.model

import org.junit.Assert.*

import org.junit.Test

class CurrencyInfoTest {

  val data = CurrencyInfo("BTC", "Bitcoin", "BTC")

  @Test
  fun firstLetterOfSymbol() {
    assertEquals(data.symbol[0].toString(), data.getFirstLetterOfSymbol())
  }
}