package api

import retrofit2.Call
import retrofit2.http.GET

interface GeneralAPI {
    @GET("/s/0xms62ln613n9o4/faculty.json")
    fun getListOfFaculties(): Call<ListFacultyResponse>
}