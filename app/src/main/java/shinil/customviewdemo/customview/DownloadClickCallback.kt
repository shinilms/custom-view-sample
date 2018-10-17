package shinil.customviewdemo.customview

/**
 * @author shinilms
 */

interface DownloadClickCallback {
    fun onDownload(customView: CustomView)
    fun onIdle(customView: CustomView)
}