package com.eddy.justanexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.eddy.justanexample.databinding.CurrencyListFragmentBinding
import com.eddy.justanexample.extension.ioToUi
import com.eddy.justanexample.model.CurrencyInfo
import com.eddy.justanexample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.eddy.justanexample.view.adapter.CurrencyAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

  companion object {
    fun newInstance() = CurrencyListFragment()
  }

  private val itemCallback = PublishSubject.create<CurrencyInfo>()
  fun getItemCallback(): Observable<CurrencyInfo> = itemCallback.ioToUi()

  private val viewModel by activityViewModels<MainViewModel>()
  private val binding by lazy { CurrencyListFragmentBinding.inflate(layoutInflater) }
  private var adapter: CurrencyAdapter? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView()
    initLiveData()
  }

  private fun initView() {
    adapter = CurrencyAdapter(onItemClick = { itemCallback.onNext(it) })
    binding.recyclerView.adapter = adapter
  }

  private fun initLiveData() {
    viewModel.onGetCurrencyInfo.observe(this, {
      adapter?.submitList(it)
    })
  }

  fun sortList() {
    adapter?.sortList()
  }

}