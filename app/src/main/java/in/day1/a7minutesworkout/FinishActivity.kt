package `in`.day1.a7minutesworkout

import `in`.day1.a7minutesworkout.databinding.ActivityFinishBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        Creating the dao from the database from the workout app
        val historyDao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(historyDao)


        setSupportActionBar(binding?.toolbarFinish)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarFinish?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

    }

//    Add the date to the data base
    private fun addDateToDatabase(historyDao: HistoryDao) {
        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: ", ""+dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date))
        }
    }
}