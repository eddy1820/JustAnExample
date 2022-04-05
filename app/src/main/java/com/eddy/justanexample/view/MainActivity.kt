package com.eddy.justanexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.eddy.justanexample.R
import com.eddy.justanexample.base.BaseActivity
import com.eddy.justanexample.databinding.ActivityMainBinding
import com.eddy.justanexample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxkotlin.addTo

@AndroidEntryPoint
class MainActivity : BaseActivity() {

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
  private val currencyListFragment by lazy { CurrencyListFragment.newInstance() }

  private val viewModel by viewModels<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    initView()
  }

  private fun initView() {
    supportFragmentManager.beginTransaction().apply {
      add(R.id.fragment, currencyListFragment)
      commit()
    }

    currencyListFragment.getItemCallback().subscribe {
      Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
    }.addTo(compositeDisposable)

    binding.displayBtn.setOnClickListener {
      viewModel.getCurrencyData()
    }

    binding.sortBtn.setOnClickListener {
      currencyListFragment.sortList()
    }
  }
}