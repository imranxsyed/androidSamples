package com.isyed.samples

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.facebook.login.LoginManager

class ProfileFragment : Fragment() {
    private lateinit var flLogOut :FrameLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        flLogOut = view.findViewById(R.id.fl_log_out)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        flLogOut.setOnClickListener{
            LoginManager.getInstance().logOut()
            val intent = Intent()
            intent.setClass(activity as Activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }

    companion object {
        fun newInstance() =
                ProfileFragment()
//                .apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
    }
}