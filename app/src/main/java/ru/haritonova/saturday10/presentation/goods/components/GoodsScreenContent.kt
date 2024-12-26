package ru.haritonova.saturday10.presentation.goods.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.haritonova.saturday10.R
import ru.haritonova.saturday10.data.models.GoodsItemModel
import ru.haritonova.saturday10.presentation.goods.contract.GoodsEvent
import ru.haritonova.saturday10.presentation.goods.contract.GoodsState

@Composable
fun GoodsScreenContent(
  uiState: GoodsState,
  onEvent: (GoodsEvent) -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Button(
      modifier = Modifier.padding(start = 14.dp),
      onClick = {
        onEvent(GoodsEvent.AddButtonClicked)
      }
    ) {
      Text(text = "View users")
    }
  }
  LazyColumn {
      uiState.users.forEach { item ->
        item {
          UserCard(item, onEvent)
        }
      }
    }
  }

@Preview
@Composable
private fun GoodsScreenContentPreview() {
  GoodsScreenContent(
    uiState = GoodsState(
      goodsName = "test",
      goods = listOf(
        GoodsItemModel(
          name = "Name",
          stars = 3,
          price = 123123,
          imageId = R.drawable.ershik,
          imageURL = "",
        )
      )
    )
  ) { _ -> }
}