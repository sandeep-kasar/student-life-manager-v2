package com.studentlifemanager.ui.pin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.studentlifemanager.databinding.FragmentPinBinding
import com.studentlifemanager.pin.screen.PinScreen
import com.studentlifemanager.pin.theme.StudentLifeManagerV2Theme

class PinFragment : Fragment() {
    private var _binding: FragmentPinBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPinBinding.inflate(inflater, container, false)
        val view = binding?.root
        binding?.composeView?.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                StudentLifeManagerV2Theme {
                    PinScreen()
                }
            }
        }
        return view
    }
}