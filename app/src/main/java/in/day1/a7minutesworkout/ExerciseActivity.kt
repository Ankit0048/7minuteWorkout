package `in`.day1.a7minutesworkout

import `in`.day1.a7minutesworkout.databinding.ActivityExerciseBinding
import `in`.day1.a7minutesworkout.databinding.DialogCustomBackConfirmationBinding
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityExerciseBinding?= null

    private var restTimer: CountDownTimer?= null
    private var restProgress:Int = 0

    private var exerciseTimer: CountDownTimer?= null
    private var exerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel> ?= null
    private var currentExercisePosition = -1

    private var player: MediaPlayer?= null
    private val soundUri = Uri.parse("android.resource://in.day1.a7minutesworkout/"
            +R.raw.endupsound)

    private var exerciseStatusAdapter: ExerciseStatusAdapter ?=null
    private var tts: TextToSpeech?= null

    private var restTimerDuration: Long = 10
    private var exerciseTimerDuration: Long = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Set the binding using the xml file of the activity_exercise
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        Action bar is added to the toolbarExercise
        setSupportActionBar(binding?.toolbarExercise)


//        Displaying the backbutton on the toolbar on the Action bar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
//        We then add the purpose of the button clicked on the toolbar which is back
        binding?.toolbarExercise?.setNavigationOnClickListener {
            customDialogForBack()
        }

//        Generates the list of the exercise here
        exerciseList = Constants.defaultExerciseList()

//        Set the Text to speech here
        tts = TextToSpeech(this,this)
//        Set up the rest view when the activity is launched
        setRestView()
        setupExerciseRecyler()

    }

//    Custom design what the back button on the toolbar does
    private fun customDialogForBack() {
        val customDialog = Dialog(this )
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()

        }
        dialogBinding.btnNo.setOnClickListener {
                customDialog.cancel()
        }
        customDialog.show()
    }

//    ALso to set this custom Dialog for the  button
    override fun onBackPressed() {
        customDialogForBack()
    }

    // Function to setup the ExerciseRecycler
    private fun setupExerciseRecyler() {
        binding?.rvExerciseStatus?.layoutManager = LinearLayoutManager(
            this@ExerciseActivity,LinearLayoutManager.HORIZONTAL, false)
        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseStatusAdapter

    }
//    To start the timer in the rest view of the progress bar in the activity
    private fun setRestView() {

//    Putting the sound to play
    try{
        player = MediaPlayer.create(applicationContext, soundUri)
        player?.isLooping = false
        player?.start()
    }
    catch(e: Exception){
        e.printStackTrace()
    }

    binding?.tvimageExercise?.visibility = View.GONE
    binding?.textExercise?.visibility = View.GONE
    binding?.fltimeExercise?.visibility = View.GONE
    binding?.fltime?.visibility = View.VISIBLE
    binding?.NextEx?.visibility = View.VISIBLE
    binding?.tvTitle?.visibility = View.VISIBLE
    if(restTimer != null) {
        restTimer?.cancel()
        restProgress = 0
    }
        binding?.NextExName?.text = exerciseList!![currentExercisePosition+1].getName()
        restProgressBar()
    }

//    Set the progress bar to the initial value using the rest progress
    private fun restProgressBar() {
        binding?.progressbar?.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration*1000, 1000) {
//            When a unit of countdowninterval passes it is called
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressbar?.progress = 10 - restProgress
                binding?.timertext?.text = "${10 - (restProgress)}"
            }

//            When the timer is finished in the app
            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setSelectedStatus(true)
                exerciseStatusAdapter!!.notifyDataSetChanged()
                setExerciseView()
            }

        }.start()
    }

    private fun setExerciseView() {
//        Set the view to see the exercise make the rest view invisible
        binding?.NextEx?.visibility = View.GONE
        binding?.tvTitle?.visibility = View.GONE
        binding?.fltime?.visibility = View.GONE
        binding?.fltimeExercise?.visibility = View.VISIBLE
        binding?.textExercise?.visibility = View.VISIBLE
        binding?.tvimageExercise?.visibility = View.VISIBLE
        if(exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0

        }

//      Setting the exercise image and the name of the exercise
        binding?.tvimageExercise?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.textExercise?.text = exerciseList!![currentExercisePosition].getName()
        speakOut(exerciseList!![currentExercisePosition].getName())
        exerciseProgressBar()
    }

    //    Set the progress bar to the initial value using the Exercise progress
    private fun exerciseProgressBar() {
        binding?.progressbarExercise?.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration*1000, 1000) {
            //            When a unit of countdowninterval passes it is called
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressbarExercise?.progress = 30 - exerciseProgress
                binding?.timertextExercise?.text = "${30 - (exerciseProgress)}"
            }

            //            When the timer is finished in the app
            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Exercise Finished",
                    Toast.LENGTH_SHORT).show()
                exerciseList!![currentExercisePosition].setCompletedStatus(true)
                exerciseList!![currentExercisePosition].setSelectedStatus(false)
                exerciseStatusAdapter!!.notifyDataSetChanged()
//                After completion set the rest view to the exercise
                if(currentExercisePosition < exerciseList!!.size - 1) {

                    setRestView()
                }
                else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
//                Move to the next Exercise
            }

        }.start()
    }

//    When the activity is destroyed the value should be reset
    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null) {
            restProgress = 0
            restTimer?.cancel()
        }
        if(exerciseTimer != null) {
            exerciseProgress = 0
            exerciseTimer?.cancel()
        }

        if(tts != null) {
            tts?.stop()
            tts?.shutdown()
        }

        if(player != null) {
            player?.stop()
        }

        binding = null
    }

//    Function to declare the initialize of the text to speech
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.ENGLISH)

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("tts","Language Not recognised!")
            }
        }
        else {
            Log.e("tts", "Failed to Load Text to Speech")
        }
    }

//    Function which makes what to speak out
    private fun speakOut(speech: String) {
        tts?.speak(speech, TextToSpeech.QUEUE_FLUSH, null, "")
    }


}