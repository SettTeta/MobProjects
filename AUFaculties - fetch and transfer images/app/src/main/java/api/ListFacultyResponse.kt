package api

import com.google.gson.annotations.SerializedName

data class ListFacultyResponse(


    @SerializedName("faculties") var facultyResponse: List<FacultyResponse>
)
