package api

import com.google.gson.annotations.SerializedName

data class ProfileAndGradeResponse(
    var id: Long = 0,
    var name: String = "",
    var gpa: Double = 0.0,
    var credit: Int = 0,
    var faculty: String = "",

    @SerializedName("grades") var gradeResponse: List<GradeResponse>

)

//data class GradeResponse(
//    var code: String = "",
//    var credit: Double = 0.0,
//    var name: String = "",
//    var grade: String = ""
//)


/*

{
	"id": 6412021,
	"name" : "John",
	"gpa" : 3.61,
	"credit": 142,
	"faculty": {
	    "name" : "Vincent Mary School of Science and Technology",
	    "building" : "VMS"
	}
}

 */