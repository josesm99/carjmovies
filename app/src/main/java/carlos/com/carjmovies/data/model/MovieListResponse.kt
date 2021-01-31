package carlos.com.carjmovies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieListResponse(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: List<MovieData>,
    @SerializedName("total_pages") val totalPages: Int? = null,
    @SerializedName("total_results") val totalResults: Int? = null
) : Parcelable