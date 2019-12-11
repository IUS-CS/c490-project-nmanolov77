package edu.ius.c490.spacexnotifier.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.ius.c490.spacexnotifier.R
import edu.ius.c490.spacexnotifier.util.detailMission
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (detailMission?.details == null){
            mission_text_view.text = "Mission: " + detailMission?.mission_name!!
            time_text_view.text =  "Launch Time: " + detailMission?.launch_date_local!!
            rocket_name_view.text = "Rocket Type: " + detailMission?.rocket?.rocket_name!!
            launchsite_text_view.text = "Launch Site: " + detailMission?.launch_site?.site_name
            mission_detail_view.text = "Details: Details on this launch are not available at this moment, please check back at a later date."
        } else {
            mission_text_view.text = "Mission: " + detailMission?.mission_name!!
            time_text_view.text = "Launch Time: " + detailMission?.launch_date_local!!
            rocket_name_view.text = "Rocket Type: " + detailMission?.rocket?.rocket_name!!
            launchsite_text_view.text = "Launch Site: " + detailMission?.launch_site?.site_name
            mission_detail_view.text = "Details: " + detailMission?.details
        }
    }

}
