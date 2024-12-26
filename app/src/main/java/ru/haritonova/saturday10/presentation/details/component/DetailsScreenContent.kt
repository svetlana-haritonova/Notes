package ru.haritonova.saturday10.presentation.details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.ramcosta.composedestinations.annotation.Destination
import ru.haritonova.saturday10.data.models.UserModel

@OptIn(ExperimentalGlideComposeApi::class)
@Destination
@Composable
fun DetailsScreenContent(
  user: UserModel,
) {
  Column {
    GlideImage(
      model = user.imageUrl,
      contentDescription = "Profile Picture",
      modifier = Modifier
        .size(200.dp)
        .clip(CircleShape)
        .border(8.dp, Color.Black, CircleShape)
        .align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.height(40.dp))
    Box(
      modifier = Modifier
        .padding(16.dp)
        .background(Color.LightGray, RoundedCornerShape(16.dp))
        .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
        .wrapContentSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Text(
          text = "login: ${user.login}",
          fontSize = 34.sp,
          color = Color.Black,
          textAlign = TextAlign.Center,
          modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "id: ${user.id}",
          fontSize = 34.sp,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "type: ${user.type}",
          fontSize = 24.sp,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "user_view_type: ${user.user_view_type}",
          fontSize = 24.sp,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
      }
    }
  }
}



@Composable
@Preview(showBackground = true)
private fun DetailsPreview() {
  DetailsScreenContent(
    UserModel(
      imageUrl = "",
      login = "test",
      id = "1",
      type = "user",
      user_view_type = "public"
    )
  )
}