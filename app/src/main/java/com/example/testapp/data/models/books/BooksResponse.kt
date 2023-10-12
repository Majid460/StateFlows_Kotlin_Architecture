package com.example.testapp.data.models.books

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class BooksResponse(
    @PrimaryKey
    @SerializedName("_id")
    var _id:String,
    @SerializedName("id")
    var id:String?=null,
    @SerializedName("title")
    var title:String?=null,
    @SerializedName("author")
    var author:String?=null,
    @SerializedName("about")
    var about:String?=null,
    @SerializedName("url")
    var url:String?=null,
): Parcelable
