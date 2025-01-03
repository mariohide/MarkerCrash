package code.bug.map

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun addMarker(latLng: LatLng) {
        val curMarkers = uiState.value.markers
        _uiState.update { it.copy(markers = curMarkers + latLng) }
    }
}

data class UiState(
    val markers: List<LatLng> = emptyList()
)