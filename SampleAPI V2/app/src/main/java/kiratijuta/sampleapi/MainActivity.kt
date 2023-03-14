package kiratijuta.sampleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.GeneralAPI
import api.GradeResponse
import api.ProfileAndGradeResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gradeRecView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl("https://dl.dropboxusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val generalAPI = retrofit.create(GeneralAPI::class.java)

        val getProfileResponse: Call<ProfileAndGradeResponse> = generalAPI.getProfileAndGrades()
        getProfileResponse.enqueue(object : Callback<ProfileAndGradeResponse> {
            override fun onResponse(
                call: Call<ProfileAndGradeResponse>,
                response: Response<ProfileAndGradeResponse>
            ) {
                var result = response.body()
                if (result != null) {
                    displayId.text = "${result.id}"
                    displayName.text = result.name
                    displayGPA.text = "${result.gpa}"
                    displayCredit.text = "(${result.credit})"
                    displayFaculty.text = "${result.faculty}"

                    val gradesData = result.gradeResponse

                    gradeRecView.adapter = GradeAdapter(sampleGrades(gradesData))

                }
            }

            override fun onFailure(call: Call<ProfileAndGradeResponse>, t: Throwable) {
                Log.e("GENERAL-API", "Failed to request GoogleResponse ${t.message}")
            }
        })
    }

    private fun sampleGrades(gradesData: List<GradeResponse>): List<GradeResponse> {
        val g = mutableListOf<GradeResponse>()
        for (i in 0 until 8) {
            val g = GradeResponse()
            g.name = gradesData[i].name
            g.credit = gradesData[i].credit
            g.code = gradesData[i].code
            g.grade = gradesData[i].grade
        }

        return gradesData
    }

    inner class GradeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseName = itemView.displayCourseName
        val courseCode = itemView.displayCourseCode
        val courseCredit = itemView.displayCourseCredit
        val courseGrade = itemView.displayCourseGrade
    }

    inner class GradeAdapter(var grades: List<GradeResponse>) :
        RecyclerView.Adapter<GradeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
            val view = layoutInflater.inflate(R.layout.row_item, parent, false)
            return GradeHolder(view)
        }

        override fun onBindViewHolder(holder: GradeHolder, position: Int) {
            holder.courseCode.text = grades[position].code
            holder.courseGrade.text = grades[position].grade
            holder.courseCredit.text = grades[position].credit.toString()
            holder.courseName.text = grades[position].name

        }

        override fun getItemCount(): Int {
            return grades.size
        }

    }


}