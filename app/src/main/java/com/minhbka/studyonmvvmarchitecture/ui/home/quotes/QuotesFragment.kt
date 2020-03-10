package com.minhbka.studyonmvvmarchitecture.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.data.entities.Quote
import com.minhbka.studyonmvvmarchitecture.util.Coroutines
import com.minhbka.studyonmvvmarchitecture.util.hide
import com.minhbka.studyonmvvmarchitecture.util.show
import com.minhbka.studyonmvvmarchitecture.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {
    override val kodein by kodein()

    private val factory : QuotesViewModelFactory by instance()


    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.quotes.await().observe(this, Observer {
            progress_bar.hide()
            initRecycleView(it.toQuoteItem())
        })
    }

    private fun initRecycleView(quoteItems: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItems)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

}
