

package com.kuke.common.utils

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*
/**
 * Activity管理者
 *
 * @date: 2020-3-26
 * @version: 1.0
 * @author: wangchenxing
 */
class ActivityManger private constructor() {

    companion object {
        private var activityList: ArrayList<WeakReference<Activity>>? = null

        private var instance: ActivityManger? = null

        @Synchronized
        fun getInstance(): ActivityManger {
            if (instance == null) {
                instance = ActivityManger()
            }
            if (activityList == null) {
                activityList = ArrayList()
            }
            return instance!!
        }
    }

    @Synchronized
    fun addActivity(activity: Activity) {
        if (activityList == null) {
            activityList = ArrayList()
        }
        if (activityList != null) {
            activityList!!.add(WeakReference(activity))
        }
    }

    @Synchronized
    fun removeActivity(activity: Activity) {
        if (activityList != null) {
            for (weak in activityList!!) {
                if (weak.get() != null) {
                    if (weak.get() === activity) {
                        activityList?.remove(weak)
                        break
                    }
                }
            }
        }
    }

    fun getTaskActivitys(): ArrayList<WeakReference<Activity>>? {
        return activityList
    }

}