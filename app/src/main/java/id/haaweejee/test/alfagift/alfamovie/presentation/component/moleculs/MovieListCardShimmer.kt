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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.ShimmerLoading

@Composable
fun MovieListCardShimmer(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xFF444444), shape = RoundedCornerShape(8.dp))
            .clickable {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ShimmerLoading(
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(12.dp)
            ) {
                ShimmerLoading(
                    modifier = Modifier
                        .width(120.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(8.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(2.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(4.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .width(60.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(4.dp))
                ShimmerLoading(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieListCardShimmerPreview(modifier: Modifier = Modifier) {
    MovieListCardShimmer()
}