package sett.teta.aufaculties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.FacultyResponse
import api.GeneralAPI
import api.ListFacultyResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_faculty.view.*
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

        facultyRecView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl("https://dl.dropboxusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val generalAPI = retrofit.create(GeneralAPI::class.java)

        val getListFacultyResponse: Call<ListFacultyResponse> = generalAPI.getListOfFaculties()
        getListFacultyResponse.enqueue(object : Callback<ListFacultyResponse>{
            override fun onResponse(
                call: Call<ListFacultyResponse>,
                response: Response<ListFacultyResponse>
            ) {
                val result = response.body()
                if (result != null){
                    val facultyData = result.facultyResponse

                    facultyRecView.adapter = FacultyAdapter(sampleFaculty(facultyData))
                }
            }

            override fun onFailure(call: Call<ListFacultyResponse>, t: Throwable) {
                Log.e("GENERAL-API", "Failed to request GoogleResponse ${t.message}")
            }
        })
    }

    private fun sampleFaculty(facultyData: List<FacultyResponse>): List<FacultyResponse>{
        val sampleData = mutableListOf<FacultyResponse>()
        for ( i in facultyData.indices) {
            val f = FacultyResponse()
            f.FacultyName = facultyData[i].FacultyName
            f.Abbreviation = facultyData[i].Abbreviation
            f.ImageLogoName = facultyData[i].ImageLogoName
            sampleData.add(f)
        }
        return sampleData
    }


    inner class FacultyHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val facName = itemView.nameTextView
        val facAbb = itemView.abbTextView
        val facLogo = itemView.logoImageView
        var facLat = ""
        var facLong = ""
        var facInName = ""
        var facInAbb = ""
        var facInLogo = ""

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(v!!.context, ViewFaculty::class.java)
            intent.putExtra("name", facInName)
            intent.putExtra("abb", facInAbb)
            intent.putExtra("logo", facInLogo)
            intent.putExtra("long", facLat)
            intent.putExtra("lat", facLong)

            startActivity(intent)
        }
    }

    inner class FacultyAdapter(var faculties: List<FacultyResponse>) : RecyclerView.Adapter<FacultyHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyHolder {
            val view = layoutInflater.inflate(R.layout.row_item, parent, false)
            return FacultyHolder(view)
        }

        override fun onBindViewHolder(holder: FacultyHolder, position: Int) {
            holder.facName.text = faculties[position].FacultyName
            holder.facAbb.text = faculties[position].Abbreviation

            holder.facLat = faculties[position].LocationLat
            holder.facLong = faculties[position].LocationLong
            holder.facInLogo = faculties[position].ImageLogoName
            holder.facInAbb = faculties[position].Abbreviation
            holder.facInName = faculties[position].FacultyName

            Glide.with(holder.itemView.context)
                .load(faculties[position].ImageLogoName)
                .into(holder.facLogo)

        }

        override fun getItemCount(): Int {
            return faculties.size
        }

    }

}