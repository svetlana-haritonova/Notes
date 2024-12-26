package ru.haritonova.saturday10.presentation.goods.contract

import ru.haritonova.saturday10.data.models.UserModel

sealed interface GoodsEffect {

  data class OpenDetails(val item: UserModel): GoodsEffect
}