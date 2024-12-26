package ru.haritonova.saturday10.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(

  @SerializedName("login")
  val login: String,

  @SerializedName("id")
  val id: String,

  @SerializedName("avatar_url")
  val imageUrl: String,

  @SerializedName("type")
  val type: String,

  @SerializedName("user_view_type")
  val user_view_type: String,

)
