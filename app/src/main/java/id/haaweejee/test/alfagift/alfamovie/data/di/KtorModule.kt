package id.haaweejee.test.alfagift.alfamovie.data.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.service.MovieDBApiService
import id.haaweejee.test.alfagift.alfamovie.data.source.remote.service.MovieDBApiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val ktorModule = module {

    // Ktor
    factory<MovieDBApiService> { MovieDBApiServiceImpl(get()) }

    single {
        HttpClient(OkHttp) {
            // Add Chucker as an interceptor for debugging
            engine {
                // Configure OkHttp client with Chucker and logging interceptors
                preconfigured = OkHttpClient.Builder()
                    .addInterceptor(provideChuckerInterceptor(get()))  // Chucker for inspecting requests
                    .build()
            }
            install(ContentNegotiation) {
                gson()

                //Http Response
                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status:", "${response.status.value}")
                    }
                }

                // Headers
                install(DefaultRequest) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }

            }
        }

    }
}

fun provideChuckerInterceptor(context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context = context)
        .collector(ChuckerCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .build()

}