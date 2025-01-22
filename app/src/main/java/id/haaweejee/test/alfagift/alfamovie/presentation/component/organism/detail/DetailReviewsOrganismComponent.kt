package id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.test.alfagift.alfamovie.domain.entities.MovieReview
import id.haaweejee.test.alfagift.alfamovie.presentation.component.moleculs.ReviewCard

fun LazyListScope.detailReviewsOrganismComponent(
    data: List<MovieReview>
) {
    item {
        if (data.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Reviews",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                data.take(3).forEach {
                    ReviewCard(
                        review = it
                    )
                }
            }
        }

    }
}