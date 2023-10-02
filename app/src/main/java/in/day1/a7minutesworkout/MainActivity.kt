package `in`.day1.a7minutesworkout

import `in`.day1.a7minutesworkout.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding?= null
//  binding allows us to use the binding to the main view of the mainactivity Binding which allows
//    to access all the id of the view at the same time at once

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        This set the layout view as using the binding feature of the android studio
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//      Here we use the binding to access the button by direct accessing it as an element
//        val flStartButton: FrameLayout = findViewById(R.id.flstart)
        val flStartButton = binding?.flstart
        flStartButton?.setOnClickListener {
//            Starts the new intent activity of the exercise here
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

//      Set the button to start the bmi calculation activity
        val flBmiButton = binding?.flBMI
        flBmiButton?.setOnClickListener {
//            Starts the new intent activity of the exercise here
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}