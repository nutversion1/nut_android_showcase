package com.nutversion1.nutandroidshowcase.mapper

import com.nutversion1.nutandroidshowcase.api.responses.GetRandomQuoteResponse
import com.nutversion1.nutandroidshowcase.database.RandomQuoteEntity
import com.nutversion1.nutandroidshowcase.ui.RandomQuoteUi

class RandomQuoteMapper {
    fun serverToEntity(randomQuote : GetRandomQuoteResponse) = randomQuote.run{
        RandomQuoteEntity(id, content, originator.name)
    }

    fun serverToUi(randomQuote : GetRandomQuoteResponse) = randomQuote.run{
        RandomQuoteUi(content, originator.name)
    }

    fun entityToUi(randomQuoteEntity : RandomQuoteEntity) = randomQuoteEntity.run{
        RandomQuoteUi(text, name)
    }
}