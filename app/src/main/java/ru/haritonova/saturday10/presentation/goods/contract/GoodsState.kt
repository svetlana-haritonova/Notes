package ru.haritonova.saturday10.presentation.goods.contract

import ru.haritonova.saturday10.data.models.GoodsItemModel
import ru.haritonova.saturday10.data.models.UserModel

data class GoodsState(
  val goodsName: String = "",
  val goodsUrl: String = "",
  val goods: List<GoodsItemModel> = emptyList(),
  val users: List<UserModel> = emptyList()
)