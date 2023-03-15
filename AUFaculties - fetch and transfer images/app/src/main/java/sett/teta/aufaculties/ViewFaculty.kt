package sett.teta.aufaculties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_faculty.*

class ViewFaculty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_faculty)

        val facultyName = intent.getStringExtra("name")
        val facultyAbb = intent.getStringExtra("abb")
        val facultyLogo = intent.getStringExtra("logo")
        val facultyLong = intent.getStringExtra("lat")
        val facultyLat = intent.getStringExtra("long")

        viewNameTextView.text = facultyName
        viewAbbTextView.text = facultyAbb
        viewLatTextView.text = facultyLat
        viewLongTextView.text = facultyLong

            Glide.with(this)
                .load(facultyLogo)
                .into(viewLogoImageView)
    }
}
