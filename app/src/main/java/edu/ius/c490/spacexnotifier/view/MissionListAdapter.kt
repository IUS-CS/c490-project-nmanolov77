package edu.ius.c490.spacexnotifier.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import edu.ius.c490.spacexnotifier.R
import edu.ius.c490.spacexnotifier.model.Mission
import edu.ius.c490.spacexnotifier.util.detailMission
import edu.ius.c490.spacexnotifier.view.MainFragmentDirections


import kotlinx.android.synthetic.main.mission_item.view.*

private const val TAG = "MissionListAdapter"

class MissionListAdapter(val missionList: ArrayList<Mission>) :
    RecyclerView.Adapter<MissionListAdapter.PhotoViewHolder>() {

    fun updateMissionlist(newPhotosList: List<Mission>) {
        missionList.clear()
        missionList.addAll(newPhotosList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(
            R.layout.mission_item
            , parent, false
        )
        return PhotoViewHolder(view)
    }

    override fun getItemCount() = missionList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        holder.view.setOnClickListener {
            val action = MainFragmentDirections.actionGoDetail()
            detailMission = missionList[position]
            Navigation.findNavController(it)
                .navigate(action)
        }
        holder.view.missionName.text = missionList[position].mission_name
    }
    class PhotoViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}