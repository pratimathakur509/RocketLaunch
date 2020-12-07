package rocketlaunch.util.DataUtil

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

        fun getDate(dateStr: String):String {
            try {
                /** DEBUG dateStr = '2006-04-16T04:00:00Z' **/
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                val mDate = formatter.parse(dateStr) // this never ends while debugging
                return mDate.toString()
                Log.e("mDate", mDate.toString())
            } catch (e: Exception) {
                Log.e("mDate", e.toString()) // this never gets called either
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val currentDate = sdf.format(Date())
                return currentDate.toString()

            }
        }
}