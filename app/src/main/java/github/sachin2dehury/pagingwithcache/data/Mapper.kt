package github.sachin2dehury.pagingwithcache.data

import github.sachin2dehury.pagingwithcache.data.database.BeerEntity
import github.sachin2dehury.pagingwithcache.data.repository.BeerResponse
import github.sachin2dehury.pagingwithcache.data.ui.Beer

fun BeerResponse.toBeerEntity() = BeerEntity(
    brewersTips = brewersTips,
    contributedBy = contributedBy,
    description = description,
    firstBrewed = firstBrewed,
    id = id,
    imageUrl = imageUrl,
    name = name,
    ph = ph,
    srm = srm,
    tagline = tagline
)

fun BeerEntity.toBeer() = Beer(
    brewersTips = brewersTips,
    contributedBy = contributedBy,
    description = description,
    firstBrewed = firstBrewed,
    id = id,
    imageUrl = imageUrl,
    name = name,
    ph = ph,
    srm = srm,
    tagline = tagline
)