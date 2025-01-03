package code.bug.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberMarkerState

private const val TAG = "ComposeMapFragment"

class ComposeMapFragment : Fragment() {
    private val viewModel by activityViewModels<MainViewModel>()

    companion object {
        fun newInstance(): Fragment {
            return ComposeMapFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MapWithMarker(viewModel.uiState.collectAsStateWithLifecycle().value)
            }
        }
    }
}

@Composable
fun MapWithMarker(state: UiState) {
    Log.d(TAG, "MapWithMarker: ")
    GoogleMap(modifier = Modifier.fillMaxSize()) {
        state.markers.forEach {
            MarkerComposable(state = rememberMarkerState(position = it)) {
                Image(
                    painter = painterResource(R.drawable.baseline_build_24),
                    contentDescription = null
                )
            }
        }
    }
}