package mu.shurman.a7minuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mu.shurman.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flstart?.setOnClickListener {
            val intent = Intent(this,ExcerciseActivity::class.java)
            startActivity(intent)
    }
        binding?.flBMI?.setOnClickListener {
           val intent = Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }
        binding?.flHistory?.setOnClickListener {
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }



}
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    }
