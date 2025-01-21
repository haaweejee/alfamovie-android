package id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.test.alfagift.alfamovie.presentation.util.simplifyNumber

@Composable
fun Rating(
    voteAverage: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.Star,
            contentDescription = "favorite",
            tint = Color.Yellow,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = voteAverage.simplifyNumber().toString(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color.White,
        )
    }
}
