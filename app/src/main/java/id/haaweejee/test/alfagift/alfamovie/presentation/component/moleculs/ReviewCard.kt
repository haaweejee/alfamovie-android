package id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.presentation.util.formatDate

@Composable
fun ReviewCard(
    review: MovieReview,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF444444),
        ),
        modifier = modifier
            .padding(4.dp)
            .clickable {
                expanded = !expanded
            },
    ) {
        Column(
            modifier = if (expanded) {
                modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            } else {
                modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(100.dp)
            },
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(review.avatar)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image_loading_placeholder),
                    error = painterResource(
                        R.drawable.image_loading_placeholder
                    ),
                    contentDescription = "poster_path",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .clip(CircleShape)
                        .height(50.dp)
                        .width(50.dp),
                )
                Spacer(modifier = modifier.width(8.dp))
                Column {
                    Text(
                        text = review.author,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = review.contentCreated.formatDate(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = review.content,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = if (expanded) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}