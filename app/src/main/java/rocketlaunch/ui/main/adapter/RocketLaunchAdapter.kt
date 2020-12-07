package rocketlaunch.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rocketlaunch.R
import rocketlaunch.data.model.RocketLaunchData
import rocketlaunch.util.DataUtil.DateFormatter


class RocketLaunchAdapter(private val rocketLaunchData: ArrayList<RocketLaunchData>) : RecyclerView.Adapter<RocketLaunchAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rocketImage_iv: ImageView = itemView.findViewById(R.id.rocketImage_iv)
        private val rocketName_tv : TextView = itemView.findViewById(R.id.rocketName_tv)
        private val rocketLaunchDate_tv : TextView = itemView.findViewById(R.id.rocketLaunchDate_tv)
        private val rocketLaunchSuccess_tv : TextView = itemView.findViewById(R.id.rocketLaunchSuccess_tv)

        fun bind(rocketLaunchData: RocketLaunchData) {
            itemView.apply {

                rocketName_tv.text = rocketLaunchData.name

                var date:String = rocketLaunchData.date
                var formattedDate = DateFormatter()
                rocketLaunchDate_tv.text = "Launch Date: " + formattedDate.getDate(date).toString()

                if(rocketLaunchData.success == true){
                    rocketLaunchSuccess_tv.text = "Mission Success"
                }else{
                    rocketLaunchSuccess_tv.text = "Mission Failure"
                }
                if(rocketLaunchData.links?.patch?.smallImage != null) {
                    Glide.with(rocketImage_iv.context)
                            .load(rocketLaunchData.links?.patch?.smallImage)
                            .placeholder(context.resources.getDrawable(R.drawable.ic_launcher_background))
                            .fallback(context.resources.getDrawable(R.drawable.ic_launcher_background))
                            .into(rocketImage_iv)
                }else{
                    Glide.with(rocketImage_iv.context)
                            .load(context.resources.getDrawable(R.drawable.ic_launcher_background))
                            .placeholder(context.resources.getDrawable(R.drawable.ic_launcher_background))
                            .fallback(context.resources.getDrawable(R.drawable.ic_launcher_background))
                            .into(rocketImage_iv)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = rocketLaunchData.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(rocketLaunchData[position])
    }

    fun addData(launchData: List<RocketLaunchData>) {
        this.rocketLaunchData.apply {
            clear()
            addAll(launchData)

        }

    }
}


