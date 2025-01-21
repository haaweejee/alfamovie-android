package id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.haaweejee.test.alfagift.alfamovie.R

@Composable
fun GridViewButton(
    modifier: Modifier = Modifier,
    isGridView: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = modifier
        .background(color = Color(0xFF444444), shape = RoundedCornerShape(12.dp))
        .padding(10.dp)
        .clickable {
            onClick()
        }) {
        Icon(
            painter = painterResource(id = if (isGridView) R.drawable.component_ic_view_list else R.drawable.component_ic_view_grid),
            contentDescription = null,
            tint = Color.White
        )
    }
}
