package ru.haritonova.saturday10.presentation.goods.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.haritonova.saturday10.data.models.UserModel
import ru.haritonova.saturday10.presentation.goods.contract.GoodsEvent

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserCard(
  userModel: UserModel,
  onEvent: (GoodsEvent) -> Unit,
) {
  ElevatedCard(
    onClick = {
      onEvent(GoodsEvent.OnUserItemClick(userModel))
    }
  ) {
    Box(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.LightGray)
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          GlideImage(
            model = userModel.imageUrl,
            contentDescription = null,
            modifier = Modifier
              .size(100.dp)
              .clip(RoundedCornerShape(12.dp))
          )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
          modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width((100.dp.value * 3).dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
          ) {
            Text(
              textAlign = TextAlign.Center,
              text = userModel.login,
              fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              textAlign = TextAlign.Center,
              text = userModel.id,
              fontSize = 24.sp
            )
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
    }
  }
}

@Composable
@Preview
private fun UserCardPreview() {
  UserCard(
    UserModel("test", "1", "", "", ""), {}
  )
}