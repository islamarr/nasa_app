package com.adyen.android.assignment.features.main_screen.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adyen.android.assignment.common.CURRENT_FAVORITE_ID
import com.squareup.moshi.Json
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite")
@Parcelize
data class AstronomyPicture(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Json(name = "service_version")
    val serviceVersion: String? = null,
    val title: String? = null,
    val explanation: String? = null,
    val date: String? = null,
    @Json(name = "media_type")
    val mediaType: String? = null,
    @Json(name = "hdurl")
    val hdUrl: String? = null,
    val url: String? = null,
) : Parcelable