package id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailMovieColumnInformation(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .height(2.dp)
                .border(1.dp, color = Color.White)
                .widthIn(min = 50.dp, max = 100.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun DetailMovieColumnInformationPreview() {
    DetailMovieColumnInformation(
        title = "Test",
        value = "Test Value untuk informasi movie"
    )

}