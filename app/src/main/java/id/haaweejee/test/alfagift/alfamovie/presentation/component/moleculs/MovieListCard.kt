package id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import id.haaweejee.test.alfagift.alfamovie.R
import id.haaweejee.test.alfagift.alfamovie.domain.entities.Movie
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.Rating
import id.haaweejee.test.alfagift.alfamovie.presentation.util.localeDateDayParseHalfMonthSecond

@Composable
fun MovieListCard(
    modifier: Modifier = Modifier,
    data: Movie,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF444444), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                data.poster,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
                placeholder = painterResource(
                    R.drawable.image_loading_placeholder
                ),
                error = painterResource(
                    R.drawable.image_loading_placeholder
                )
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(12.dp)
            ) {
                Text(
                    data.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    data.overview,
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Release Date: ${data.releaseDate.localeDateDayParseHalfMonthSecond()}",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(4.dp))
                Rating(voteAverage = data.rating, modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
fun MovieListCardPreview(modifier: Modifier = Modifier) {
    MovieListCard(
        data = Movie(
            11111,
            "Kraven the Hunter",
            poster = "",
            releaseDate = "2024-12-11",
            rating = 0.0,
            overview = ""
        )
    ) { }
}