package edu.ius.c490.spacexnotifier.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import edu.ius.c490.spacexnotifier.view.MissionListAdapter
import edu.ius.c490.spacexnotifier.R
import edu.ius.c490.spacexnotifier.viewmodel.RocketViewModel
import kotlinx.android.synthetic.main.fragment_main.*

private const val TAG = "MainFragment"

class MainFragment : Fragment() {

    private lateinit var viewModel: RocketViewModel
    private val missionListAdapter = MissionListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refreshLayout.setOnRefreshListener {

            viewModel.getMission()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RocketViewModel::class.java)
        viewModel.getMission()

        missionList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = missionListAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.missions.observe(this, Observer { missions ->
            missions?.let {
                missionList.visibility = View.VISIBLE
                missionListAdapter.updateMissionlist(missions)
            }
        })

        viewModel.loading.observe(this, Observer {isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE

            }
        })
    }
}
