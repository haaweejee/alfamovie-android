package id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie

@Composable
fun MovieGridCard(
    modifier: Modifier = Modifier,
    data: Movie,
    onClick: () -> Unit
) {
    AsyncImage(
        data.poster,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        placeholder = painterResource(
            R.drawable.image_loading_placeholder
        )
    )
}