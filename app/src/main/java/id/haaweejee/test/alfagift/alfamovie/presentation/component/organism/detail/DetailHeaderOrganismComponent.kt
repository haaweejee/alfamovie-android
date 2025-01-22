package id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.detailHeaderOrganismComponent(
    data: DetailMovie,
    onFinish: () -> Unit
) {
    stickyHeader {
        Box(modifier = Modifier) {
            AsyncImage(
                data.backdrop,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                placeholder = painterResource(
                    R.drawable.image_loading_placeholder
                ),
                error = painterResource(
                    R.drawable.image_loading_placeholder
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(color = Color(0xFF444444).copy(alpha = 0.6f)),
            )
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onFinish.invoke()
                        }
                )
            }
            AsyncImage(
                data.poster,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(4.dp)),
                placeholder = painterResource(
                    R.drawable.image_loading_placeholder
                ),
                error = painterResource(
                    R.drawable.image_loading_placeholder
                )
            )
        }
    }
}
