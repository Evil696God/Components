package com.kuke.common.utils

import android.content.Context
import android.util.Log
import de.mindpipe.android.logging.log4j.LogConfigurator
import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.io.File
import java.util.*

/**
 * Log日志 工具类
 *
 * @date: 2018-12-11
 * @version: 1.0
 * @author: zhangtao
 */
class LogManagementUtils private constructor() {

    companion object {
        @Volatile
        private var logger: Logger? = null

        /**
         * 清理日志间隔天数
         */
        private const val cleanLogTime = 5L

        private fun getLogger(): Logger? {
            if (logger == null) {
                synchronized(LogManagementUtils::class.java) {
                    if (logger == null) {
                        logger = Logger.getRootLogger()
                    }
                }
            }
            return logger
        }

        fun e(message: String?) {
            processingStringNewline(message.toString()) { isFirstRow, newMessage ->
                if (isFirstRow) {
                    getLogger()?.error(getFunctionName() + newMessage)
                } else {
                    getLogger()?.error(newMessage)
                }
            }
        }

        fun w(message: String?) {
            processingStringNewline(message.toString()) { isFirstRow, newMessage ->
                if (isFirstRow) {
                    getLogger()?.warn(getFunctionName() + newMessage)
                } else {
                    getLogger()?.warn(newMessage)
                }
            }
        }

        fun d(message: String?) {
            processingStringNewline(message.toString()) { isFirstRow, newMessage ->
                if (isFirstRow) {
                    getLogger()?.debug(getFunctionName() + newMessage)
                } else {
                    getLogger()?.debug(newMessage)
                }
            }
        }

        fun i(message: String?) {
            processingStringNewline(message.toString()) { isFirstRow, newMessage ->
                if (isFirstRow) {
                    getLogger()?.info(getFunctionName() + newMessage)
                } else {
                    getLogger()?.info(newMessage)
                }
            }
        }

        /**
         * 字符串换行
         */
        private fun processingStringNewline(message: String, function: (isFirstRow: Boolean, newMessage: String) -> Unit) {
            var tempMessage = message
            val separateLine = "                                                                                                                                                                                                                                                                        "
            var messageLength = tempMessage.length
            val maxLength = 2001 - getFunctionName().length - separateLine.length
            var isFirstRow = true
            while (messageLength > 0) {
                if (messageLength - maxLength > 0) {
                    function(isFirstRow, tempMessage.substring(0, maxLength) + separateLine)
                    tempMessage = tempMessage.substring(maxLength)
                    messageLength -= maxLength
                } else {
                    function(isFirstRow, tempMessage.substring(0, messageLength) + separateLine)
                    messageLength -= maxLength
                }
                isFirstRow = false
            }
        }

        /**
         * 获取方法名
         * @return
         */
        private fun getFunctionName(): String {
            val stackTraceElementList = Thread.currentThread().stackTrace ?: return ""
            for (stackTraceElement in stackTraceElementList) {
                if (stackTraceElement.isNativeMethod) {
                    continue
                }
                if (stackTraceElement.className.contains(Thread::class.java.name)) {
                    continue
                }
                if (stackTraceElement.className.contains(LogManagementUtils::class.java.getName())) {
                    continue
                }
                if (stackTraceElement.className.contains(LogManagementUtils.javaClass.name)) {
                    continue
                }
                return ("[ "
                        + Thread.currentThread().name + ": "
                        + stackTraceElement.fileName.replace(".java", "") + ":"
                        + stackTraceElement.lineNumber + " ]  ")
            }
            return ""
        }

        @JvmStatic
        fun showLog(message: String?) {
            if (message == null) {
                Log.e("++message", "++null")
            } else {
                Log.e("++message", "++$message")
            }
        }

        /**
         * Log日志存储初始化(Android6.0以上版本要求申请权限,禁止在application中初始化)
         */
        fun initLogger(context: Context) {
            val loggerPath: String = FileUtils.getStorageLoggerDirectory(context) + File.separator + DateUtil.dateFormat(Date(System.currentTimeMillis()), "yyyy-MM-dd")
            val logDirectory = File(loggerPath)
            if (!logDirectory.exists()) {
                try {
                    //按照指定的路径创建文件夹
                    logDirectory.mkdirs()
                } catch (e: Exception) {
                    println("" + e)
                }
            }
            val logConfigurator = LogConfigurator()
            //日志名称
            logConfigurator.fileName = loggerPath + File.separator + DateUtil.dateFormat(Date(System.currentTimeMillis()), "yyyy-MM-dd") + "-log.txt"
            //级别
            logConfigurator.rootLevel = Level.INFO
            logConfigurator.filePattern = "%d %m%n"
            //是否本地控制台打印输出 默认为true ，false不输出
            logConfigurator.isUseLogCatAppender = true
            //文件最大10M
            logConfigurator.maxFileSize = 1024L * 1024L * 10L
            //最大备份日志文件的个数为1个（也就是会生成一个log.txt.1的文件;如果设为2，就表示有log.txt.1和log.txt.2；当然最新的日志还是存在log.txt中；如果想看前面的日志，可以看备份文件。）
            logConfigurator.maxBackupSize = 5
            //设置所有消息是否被立刻输出 默认为true,false 不输出
            logConfigurator.isImmediateFlush = true
            logConfigurator.configure()
        }

        /**
         * log日志清理
         */
        fun cleanupLog(context: Context) {
            // 获取log文件夹下所有文件
            val fileList = File(FileUtils.getStorageLoggerDirectory(context)).listFiles()
            fileList?.forEach {
                val tempTimeMillis = DateUtil.getTimeMillisFromString(it.name, "yyyy-MM-dd")
                val currentTimeMillis = System.currentTimeMillis()
                if (currentTimeMillis - tempTimeMillis > DateUtil.getTimeMillisFromDay(cleanLogTime)) {
                    FileUtils.deleteFile(it)
                }
            }
        }
    }
}