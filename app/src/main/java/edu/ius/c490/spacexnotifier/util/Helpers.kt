package edu.ius.c490.spacexnotifier.util

import android.content.Context
import android.content.DialogInterface
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import edu.ius.c490.spacexnotifier.R
import edu.ius.c490.spacexnotifier.model.Mission

var detailMission: Mission? = null

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 3f
        centerRadius = 20f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun showAlert(context: Context){
    val dialogBuilder = AlertDialog.Builder(context)

    dialogBuilder.setMessage("This app was developed by Nick Manolov")
        .setCancelable(true)
        .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                dialog, id -> dialog.cancel()
        })
    val alert = dialogBuilder.create()
    alert.setTitle("SpaceX Notifier")
    alert.show()
}
