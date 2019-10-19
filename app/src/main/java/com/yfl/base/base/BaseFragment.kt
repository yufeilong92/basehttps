package com.yfl.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.backpacker.yflLibrary.kotlin.PermissionUtils
import com.backpacker.yflLibrary.kotlin.T
import com.backpacker.yflLibrary.kotlin.KotlinUtil
import com.backpacker.yflLibrary.view.MyProgreeDialog
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.trello.rxlifecycle3.components.support.RxFragment
import com.yfl.base.manage.ResultFragmentTo
import com.yfl.base.retrofit.net.HttpBaseResult

/**
 * @Author : YFL  is Creating a porject in tsyc
 * @Package com.backpacker.UtilsLibrary.base
 * @Email : yufeilong92@163.com
 * @Time :2019/7/4 16:43
 * @Purpose :碎片基类
 */
abstract class BaseFragment : RxFragment() {


    protected var isFirstEnter = true //是否是第一次进入,默认是
    private var isReuseView = true //是否进行复用，默认复用
    private var isFragmentVisible: Boolean = false
    private var rootView: View? = null


    lateinit var mResultTo: ResultFragmentTo
    lateinit var mContext: RxAppCompatActivity
    lateinit var progressDialog: MyProgreeDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setContentView(), container, false)
    }
    abstract fun setContentView(): Int
    abstract fun setInitCreatedContentView(view: View, savedInstanceState: Bundle?)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mResultTo = ResultFragmentTo(activity!!)
        mContext = (this.activity as RxAppCompatActivity?)!!
        progressDialog = MyProgreeDialog(mContext)
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        setInitCreatedContentView(view, savedInstanceState)
        if (rootView == null) {
            rootView = view
            if (userVisibleHint) {
                if (isFirstEnter) {
                    onFragmentFirstVisible(view)
                    isFirstEnter = false
                }
                onFragmentVisibleChange(true)
                isFragmentVisible = true
            }
        }
    }

    private fun test() {
        PermissionUtils.showPermission(mContext, "", arrayOf("")) {

        }
    }


    fun showProgress() {
        KotlinUtil.showDialog(progressDialog)
    }

    fun dismissProgress() {
        KotlinUtil.dismissDialog(progressDialog)
    }

    fun onError(ex: Throwable) {
        dismissProgress()
        HttpBaseResult.onError(mContext, ex)
    }

    fun onComplate() {
        dismissProgress()
        HttpBaseResult.onComplate()
    }

    fun getStringWithId(id: Int): String {
        return mContext.resources.getString(id)
    }

    fun toShowToasct(content: String) {
        T.showToast(mContext, content)
    }

    open fun onHomeBack(v: View) {
        mResultTo.finishBase()
    }

    var mDialog: AlertDialog? = null
  /*  fun showAlerdialog(
        title: String,
        sure: String,
        canner: String,
        no: () -> Unit,
        ok: () -> Unit
    ) {
        mDialog = DialogUtil.showDialogTitle(
            mContext,
            "",
            title,
            sure,
            canner,
            true,
            cannerlistener = {
                no()
            },
            sureList = {
                ok()
            })
    }
*/
    override fun onDestroyView() {
        super.onDestroyView()
        KotlinUtil.dismissDialog(mDialog)
        KotlinUtil.dismissDialog(mSureLoginDialog)
    }


    private var mSureLoginDialog: AlertDialog? = null
 /*   fun isLogin(mDbHelp:UserDbHelp?): Boolean {
        if (mDbHelp != null) {
            val userInfom = mDbHelp!!.getUserInfom()
            if (userInfom == null || KotlinStringUtil.isEmpty(userInfom.token)) {
                showLoginSureDialog()
                return true
            }
            return false
        } else {
            showLoginSureDialog()
            return true
        }
    }*/
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            //如果view还未初始化，不进行处理
            return
        }
        if (isFirstEnter && isVisibleToUser) {
            //如果是第一次进入并且可见
            onFragmentFirstVisible(rootView!!) //回调当前fragment首次可见
            isFirstEnter = false //第一次进入的标识改为false
        }
        if (isVisibleToUser) {
            //如果不是第一次进入，可见的时候
            isFragmentVisible = true
            onFragmentVisibleChange(isFragmentVisible) //回调当前fragment可见
            return
        }

        if (isFragmentVisible) {
            //如果当前fragment不可见且标识为true
            isFragmentVisible = false //更改标识
            onFragmentVisibleChange(isFragmentVisible) //回调当前fragment不可见
        }
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected open fun reuseView(isReuse: Boolean) {
        isReuseView = isReuse
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     * false 可见  -> 不可见
     */
    protected open fun onFragmentVisibleChange(isVisible: Boolean) {

    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected open fun onFragmentFirstVisible(view: View) {

    }

    protected  open fun isFragmentVisible(): Boolean {
        return isFragmentVisible
    }
    /**重置变量 */
    private fun resetVariavle() {
        isFirstEnter = true
        isReuseView = true
        isFragmentVisible = false
    }
    override fun onDestroy() {
        super.onDestroy()
        resetVariavle()
    }

}