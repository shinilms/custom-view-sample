package shinil.customviewdemo.customview

import android.view.View

/**
 * @author shinilms
 */

class DownloadClickListener(private val view: CustomView, private val callback: DownloadClickCallback) : View.OnClickListener {
    override fun onClick(v: View?) {
        when (view.state) {
            State.IDLE -> {
                view.state = State.DOWNLOADING
                callback.onDownload(view)
            }
            State.DOWNLOADING -> {
                view.state = State.IDLE
                callback.onIdle(view)
            }
        }
    }
}