@file:OptIn(ExperimentalLayoutApi::class)

package id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.test.alfagift.alfamovie.domain.entities.DetailMovie
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.DetailMovieColumnInformation
import id.haaweejee.test.alfagift.alfamovie.presentation.util.detailedDuration
import id.haaweejee.test.alfagift.alfamovie.presentation.util.localeDateDay

fun LazyListScope.detailContentOrganismComponent(
    data: DetailMovie,
) {
    item {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = data.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailMovieColumnInformation(
                    title = "Release Date",
                    value = data.releaseDate.localeDateDay()
                )
                DetailMovieColumnInformation(
                    title = "Duration",
                    value = data.duration.detailedDuration(),
                )
                DetailMovieColumnInformation(
                    title = "Status",
                    value = data.status
                )
            }
            if (data.genres.isNotEmpty()) {
                Text(
                    text = "Genres",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    data.genres.forEach {
                        Box(
                            modifier = Modifier.background(
                                color = Color(0xFF444444),
                                shape = RoundedCornerShape(12.dp),
                            ),
                        ) {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = "Overview",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = data.overview,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    }
}