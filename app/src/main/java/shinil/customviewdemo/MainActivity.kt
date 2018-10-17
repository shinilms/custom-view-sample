package shinil.customviewdemo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import shinil.customviewdemo.customview.CustomView
import shinil.customviewdemo.customview.DownloadClickCallback
import shinil.customviewdemo.customview.DownloadClickListener
import shinil.customviewdemo.customview.State

/**
 * @author shinilms
 */

class MainActivity : AppCompatActivity(), DownloadClickCallback {
    private lateinit var customView1: CustomView
    private lateinit var customView2: CustomView
    private lateinit var customView3: CustomView

    private var progress1 = 0
    private var progress2 = 0
    private var progress3 = 0

    private val handler = Handler()

    private val updateCvOne = object : Runnable {
        override fun run() {
            if (progress1 < 100) {
                progress1++
                customView1.progress.progress = progress1
                handler.postDelayed(this, 30)
            } else {
                customView1.state = State.DONE
                handler.removeCallbacks(this)
            }
        }
    }

    private val updateCvTwo = object : Runnable {
        override fun run() {
            if (progress2 < 100) {
                progress2++
                customView2.progress.progress = progress2
                handler.postDelayed(this, 50)
            } else {
                customView2.state = State.DONE
                handler.removeCallbacks(this)
            }
        }
    }

    private val updateCvThree = object : Runnable {
        override fun run() {
            if (progress3 < 100) {
                progress3++
                customView3.progress.progress = progress3
                handler.postDelayed(this, 10)
            } else {
                customView3.state = State.DONE
                handler.removeCallbacks(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customView1 = findViewById(R.id.cv_one)
        customView2 = findViewById(R.id.cv_two)
        customView3 = findViewById(R.id.cv_three)

        customView1.text.text = ("Maroon 5 - Girls Like You.mp3")
        customView2.text.text = ("Passenger - Let Her Go.mp3")
        customView3.text.text = ("Imagine Dragons - Natural.mp3")

        customView1.image.setOnClickListener(DownloadClickListener(customView1, this))
        customView2.image.setOnClickListener(DownloadClickListener(customView2, this))
        customView3.image.setOnClickListener(DownloadClickListener(customView3, this))
    }

    override fun onDownload(customView: CustomView) {
        when (customView.id) {
            R.id.cv_one -> {
                progress1 = customView.progress.progress
                handler.post(updateCvOne)
            }
            R.id.cv_two -> {
                progress2 = customView.progress.progress
                handler.post(updateCvTwo)
            }
            R.id.cv_three -> {
                progress3 = customView.progress.progress
                handler.post(updateCvThree)
            }
        }
    }

    override fun onIdle(customView: CustomView) {
        when (customView.id) {
            R.id.cv_one -> {
                handler.removeCallbacks(updateCvOne)
            }
            R.id.cv_two -> {
                handler.removeCallbacks(updateCvTwo)
            }
            R.id.cv_three -> {
                handler.removeCallbacks(updateCvThree)
            }
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(updateCvOne)
        handler.removeCallbacks(updateCvTwo)
        handler.removeCallbacks(updateCvThree)
        super.onDestroy()
    }
}
