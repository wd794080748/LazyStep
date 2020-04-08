package com.wangdong.lazystep.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    private static final String DEBUG_TAG = "LogWriter";
    private static boolean isDebug = true;
    private static boolean isWriterToLog = false;

    public static void LogToFile(String tag, String logText) {
        if (!LogWriter.isWriterToLog) {
            return;
        }
        String needWriterMessage = tag + ":" + logText;
        String fileName = Environment.getExternalStorageDirectory().getPath()
                + "/LogFile.txt";
        File file = new File(fileName);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(needWriterMessage);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String tag, String msg){
        if(LogWriter.isDebug){
            Log.d(LogWriter.DEBUG_TAG+ "->>>>>>>" + tag, msg);
        }
        if(LogWriter.isWriterToLog){
            LogWriter.LogToFile(LogWriter.DEBUG_TAG+ "->>>>>>>" + "d/"+tag,msg);
        }
    }

    public static void i(String tag, String msg){
        if(LogWriter.isDebug){
            Log.i(LogWriter.DEBUG_TAG+ "->>>>>>>" + tag, msg);
        }
        if(LogWriter.isWriterToLog){
            LogWriter.LogToFile(LogWriter.DEBUG_TAG+ "->>>>>>>" + "i/"+tag,msg);
        }
    }

    public static void e(String tag, String msg){
        if(LogWriter.isDebug){
            Log.e(LogWriter.DEBUG_TAG+ "->>>>>>>" + tag, msg);
        }
        if(LogWriter.isWriterToLog){
            LogWriter.LogToFile(LogWriter.DEBUG_TAG+ "->>>>>>>" + "e/"+tag,msg);
        }
    }

    public static void v(String tag, String msg){
        if(LogWriter.isDebug){
            Log.v(LogWriter.DEBUG_TAG+ "->>>>>>>" + tag, msg);
        }
        if(LogWriter.isWriterToLog){
            LogWriter.LogToFile(LogWriter.DEBUG_TAG+ "->>>>>>>" + "v/"+tag,msg);
        }
    }

    public static void w(String tag, String msg){
        if(LogWriter.isDebug){
            Log.w(LogWriter.DEBUG_TAG+ "->>>>>>>" + tag, msg);
        }
        if(LogWriter.isWriterToLog){
            LogWriter.LogToFile(LogWriter.DEBUG_TAG+ "->>>>>>>" + "w/"+tag,msg);
        }
    }

}
