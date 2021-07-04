package com.gallery.data.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoData(
    val id: Int,
    @SerialName("previewURL") val previewUrl: String,
    @SerialName("previewWidth") val previewWidth: Int,
    @SerialName("previewHeight") val previewHeight: Int,
    @SerialName("largeImageURL") val imageUrl: String,
    @SerialName("imageWidth") val imageWidth: Int,
    @SerialName("imageHeight") val imageHeight: Int
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(previewUrl)
        dest?.writeInt(previewWidth)
        dest?.writeInt(previewHeight)
        dest?.writeString(imageUrl)
        dest?.writeInt(imageWidth)
        dest?.writeInt(imageHeight)
    }

    companion object CREATOR : Parcelable.Creator<PhotoData> {
        override fun createFromParcel(parcel: Parcel): PhotoData {
            return PhotoData(parcel)
        }

        override fun newArray(size: Int): Array<PhotoData?> {
            return arrayOfNulls(size)
        }
    }
}