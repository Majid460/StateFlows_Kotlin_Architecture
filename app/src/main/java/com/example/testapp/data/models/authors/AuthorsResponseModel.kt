package com.example.testapp.data.models.authors

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class AuthorsResponseModel(
    var _id:String,
    var name:String,
    var pic:String
): Serializable,Parcelable
