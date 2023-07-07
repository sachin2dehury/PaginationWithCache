package github.sachin2dehury.pagingwithcache.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class BeerEntity(
    val brewersTips: String? = null,
    val contributedBy: String? = null,
    val description: String? = null,
    val firstBrewed: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val ph: Double? = null,
    val srm: Double? = null,
    val tagline: String? = null,
    @PrimaryKey
    val id: Int = 0,
)