package com.example.nytimesapplication.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MostPopularArticle(
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("published_date")
    val published_date: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("geo_facet")
    val location: Array<String>?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("abstract")
    val abstract: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("type")
    val type : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(byline)
        parcel.writeString(published_date)
        parcel.writeString(title)
        parcel.writeStringArray(location)
        parcel.writeString(source)
        parcel.writeString(abstract)
        parcel.writeString(section)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MostPopularArticle> {
        override fun createFromParcel(parcel: Parcel): MostPopularArticle {
            return MostPopularArticle(parcel)
        }

        override fun newArray(size: Int): Array<MostPopularArticle?> {
            return arrayOfNulls(size)
        }
    }
}

