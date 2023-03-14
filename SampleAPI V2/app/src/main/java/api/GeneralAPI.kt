package api

import retrofit2.Call
import retrofit2.http.GET

interface GeneralAPI {

    @GET("/s/3ubyyvp2x337nyk/profileandgrades.json")
    fun getProfileAndGrades(): Call<ProfileAndGradeResponse>

}