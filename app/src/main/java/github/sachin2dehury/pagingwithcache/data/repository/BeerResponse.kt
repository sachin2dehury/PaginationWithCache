package github.sachin2dehury.pagingwithcache.data.repository

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerResponse(
    @Json(name = "brewers_tips")
    val brewersTips: String? = null,
    @Json(name = "contributed_by")
    val contributedBy: String? = null,
    val description: String? = null,
    @Json(name = "first_brewed")
    val firstBrewed: String? = null,
    val id: Int? = null,
    @Json(name = "image_url")
    val imageUrl: String? = null,
    val name: String? = null,
    val ph: Double? = null,
    val srm: Double? = null,
    val tagline: String? = null
)
