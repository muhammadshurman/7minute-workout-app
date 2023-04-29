package mu.shurman.a7minuteworkout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import mu.shurman.a7minuteworkout.databinding.ActivityExcerciseBinding
import mu.shurman.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*


class ExcerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExcerciseBinding?=null
    private var restTimer : CountDownTimer?=null
    private var restProgress = 0
    private var restTimerDuration: Long = 1
    private var exerciseTimerDuration: Long = 1

    private var excerciseTimer : CountDownTimer?=null
    private var excerciseProgress = 0

    private var excerciseList: ArrayList<ExcerciseModel>?=null
    private var currentExcercisePosition = -1

    private var tts : TextToSpeech?=null
    private var player: MediaPlayer?=null

    private var exerciseAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExcercise)

        if(supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBarExcercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        excerciseList = Constants.defaultExcerciseList()

        tts = TextToSpeech(this,this)

        setupRestView()
        setUpExerciseStatusRecycleView()

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        customDialogForBackButton()

    }
    private fun customDialogForBackButton(){
val customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@ExcerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
    private fun setUpExerciseStatusRecycleView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(excerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }
    private fun setupRestView()
    {
        try {
            val soundURI = Uri.parse(
                "android.resource://mu.shurman.a7minuteworkout/"
                        + R.raw.lofi_sport)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e:Exception){
            e.printStackTrace()
        }
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExcerciseName?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.flExcerciseView?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExcersieName?.visibility = View.VISIBLE
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restProgress = 0
        }

        binding?.tvUpcomingExcersieName?.text =
            excerciseList!![currentExcercisePosition+1].getName()
        setRestProgressBar()
    }
    @SuppressLint("SetTextI18n")
    private fun setupExcerciseView()
    {
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExcerciseName?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.flExcerciseView?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExcersieName?.visibility = View.INVISIBLE

        if(excerciseTimer!=null){
            excerciseTimer?.cancel()
            excerciseProgress = 0
        }

        speakOut(excerciseList!![currentExcercisePosition].getName())
        binding?.ivImage?.setImageResource(excerciseList!![currentExcercisePosition].getImage())
        binding?.tvExcerciseName?.text = excerciseList!![currentExcercisePosition].getName()
        setExcerciseProgressBar()
    }
    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration*5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text =
                    (10 - restProgress).toString()
           }

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {
                currentExcercisePosition++
                excerciseList!![currentExcercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExcerciseView()
            }
        }.start()
    }
    private fun setExcerciseProgressBar(){
        binding?.progressBarExcercise?.progress = excerciseProgress
        excerciseTimer = object : CountDownTimer(exerciseTimerDuration*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                binding?.progressBarExcercise?.progress = 30 - excerciseProgress
                binding?.tvTimerExcercise?.text =
                    (30 - excerciseProgress).toString()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {

                if(currentExcercisePosition<excerciseList?.size!!-1)
                {
                    excerciseList!![currentExcercisePosition].setIsSelected(false)
                    excerciseList!![currentExcercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else{
                 finish()
                    val intent = Intent(this@ExcerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }
    override fun onDestroy()
    {
        super.onDestroy()
        if(restTimer!=null)
        {
            restTimer?.cancel()
            restProgress = 0
        }

        if(tts!=null)
        {
            tts!!.stop()
            tts!!.shutdown()
        }
        if (player!=null){
            player!!.stop()
        }
        super.onDestroy()
        binding = null
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS)
        {
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA|| result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS","The Language specified is not supported!")
            }
        } else {
            Log.e("TTS","Initialization Failed!")
        }
    }
    private fun speakOut(text:String)
    {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}