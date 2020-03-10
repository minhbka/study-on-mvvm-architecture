package com.minhbka.studyonmvvmarchitecture.ui.home.quotes

import com.minhbka.studyonmvvmarchitecture.R
import com.minhbka.studyonmvvmarchitecture.data.entities.Quote
import com.xwray.groupie.databinding.BindableItem
import com.minhbka.studyonmvvmarchitecture.databinding.ItemQuoteBinding

class QuoteItem(
    private val quote:Quote
):BindableItem<ItemQuoteBinding>() {
    override fun getLayout()=  R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
       viewBinding.setQuote(quote)
    }
}