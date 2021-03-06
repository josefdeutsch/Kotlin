package net.samystudio.beaver.data.remote

import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.rxjava3.core.Single
import net.samystudio.beaver.data.model.Home
import retrofit2.http.GET
import javax.inject.Inject

interface HomeApiInterface {
    @GET("home.json")
    fun home(): Single<Home>
}

@ActivityRetainedScoped
class HomeApiInterfaceImpl @Inject constructor(private val homeApiInterface: HomeApiInterface) {
    fun home(): Single<Home> =
        homeApiInterface.home()
}
