package com.expeknow.store.network

import retrofit2.Call
import retrofit2.http.GET

const val user_content_key : String = "R9SDOsGKGJJ2w3Aj80ENQkcfZyuizk641ycrrYt90AEGyidytBZLCTBWsnaJB1JQ9xmz4YVJQJAm98H69ppYRjJxAp4T8J6tm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBYdrlLXgkVduZQQRwaWjRavxGMl-Khd3vW0hk0Qna9MJCXwhCH316-fX-swozunOkQB1B7f7oCybnWYCIRqrZPhvhb-CbDsMg&lib=MpIQkW86BAZxpo05ptZ4JtXU3Pmz4oTv4"

interface StoreService {
    @GET("s/AKfycbzSE-Z8hpxu9fK5eSxTk7rXiR8OLZGRtsh3jb3we7OvvcR8vJAySluZ5UWvjxnoIPo1/exec")
    fun getAllApps() : Call<AppData>

    @GET("featured")
    fun getFeaturedApps() : Call<AppData>
}