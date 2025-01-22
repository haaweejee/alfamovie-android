package id.haaweejee.test.alfagift.alfamovie.presentation.component.organism.detail

import androidx.compose.foundation.lazy.LazyListScope
import id.haaweejee.test.alfagift.alfamovie.presentation.component.atoms.YoutubePlayer

fun LazyListScope.detailYoutubeVideoPlayerOrganismComponent(videoKey: String) {
    item {
        YoutubePlayer(key = videoKey)
    }
}