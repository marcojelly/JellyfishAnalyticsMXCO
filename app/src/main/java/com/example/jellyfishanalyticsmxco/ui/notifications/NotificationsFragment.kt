package com.example.jellyfishanalyticsmxco.ui.notifications
// CUSTOM DATA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jellyfishanalyticsmxco.databinding.FragmentNotificationsBinding
import com.google.firebase.analytics.FirebaseAnalytics
//GEMINI STARTS
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
//GEMINI ENDS

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    //GEMINI STARTS
    // Declara la instancia de Firebase Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    //GEMINI ENDS

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    //GEMINI STARTS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // JF: Starts Firebase Analytics
        firebaseAnalytics = Firebase.analytics

        //Custom Event
        binding.sendCustomEventBtn.setOnClickListener {
            val customEventName = binding.customEventText.text.toString()
            val customParamName = binding.customParamText.text.toString()
            val customParamValue = binding.customValueText.text.toString()

            firebaseAnalytics.logEvent( customEventName ) {
                param(customParamName, customParamValue)
            }
        }

        //User ID
        binding.userIDBtn.setOnClickListener {
            val customUserID = binding.userIDText.text.toString()
            firebaseAnalytics.setUserId( customUserID )

            firebaseAnalytics.logEvent( "login" ) {
                param("method", "email")
            }
        }

        //User Property
        binding.userPropertyBtn.setOnClickListener {
            val customUserProperty = binding.userPropertyText.text.toString()
            val customUserPropertyValue = binding.userPropertyValueText.text.toString()
            firebaseAnalytics.setUserProperty( customUserProperty , customUserPropertyValue)
        }

        //Reset
        binding.resetAnalyticsBtn.setOnClickListener {
            firebaseAnalytics.resetAnalyticsData()
        }
    }

    override fun onResume() {
        super.onResume()

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "customdata_screen")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}