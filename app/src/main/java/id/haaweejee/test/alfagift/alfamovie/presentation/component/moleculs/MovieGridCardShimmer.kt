package id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.ShimmerLoading

@Composable
fun MovieGridCardShimmer(
    modifier: Modifier = Modifier,
) {
    ShimmerLoading(
        modifier = modifier
            .width(160.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}