package shinil.customviewdemo.customview

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import shinil.customviewdemo.R

/**
 * @author shinilms
 */

class CustomView : LinearLayout {
    lateinit var text: TextView
    lateinit var progress: ProgressBar
    lateinit var image: ImageView

    var state: State? = null
        set(value) {
            setDownloadState(value)
            field = value
        }

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_view_item, this, true)

        val viewGroup = getChildAt(0) as ViewGroup
        text = viewGroup.getChildAt(0) as TextView
        progress = viewGroup.getChildAt(1) as ProgressBar
        image = getChildAt(1) as ImageView

        state = State.IDLE
    }

    private fun setDownloadState(state: State?) {
        when (state) {
            State.IDLE -> {
                image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_file_download, null))
                when (progress.progress) {
                    0 -> progress.visibility = View.GONE
                    else -> progress.visibility = View.VISIBLE
                }
            }
            State.DOWNLOADING -> {
                image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_cancel, null))
                progress.visibility = View.VISIBLE
            }
            State.DONE -> {
                image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_done, null))
                progress.visibility = View.GONE
            }
        }
    }
}