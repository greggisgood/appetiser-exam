package ph.greggjover.appetiserexam.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ph.greggjover.appetiserexam.data.database.content.GenreWithContent
import ph.greggjover.appetiserexam.data.repository.ContentRepository
import javax.inject.Inject

/**
 * ViewModel class for [ContentListFragment]
 */
@HiltViewModel
class ContentListViewModel @Inject constructor(
    private val repository: ContentRepository,
) : ViewModel() {

    private val _contentLiveData = MutableLiveData<List<GenreWithContent>>()
    val contentLiveData: LiveData<List<GenreWithContent>> = _contentLiveData

    private val _contentFetchFailedEvent = LiveEvent<Unit>()
    val contentFetchFailedEvent: LiveData<Unit> = _contentFetchFailedEvent


    init {
        observeContentFromDb()
        getContent()
    }

    /**
     * Upon ViewModel initialization, a Flow is dispatched to observe stored Content from
     * the repository.
     *
     * If any changes are detected, the Flow will provide the
     * [LiveData] with the updated list of [GenreWithContent]
     */
    private fun observeContentFromDb() = viewModelScope.launch {
        repository.observeContent().collect {
            _contentLiveData.value = it
        }
    }

    /**
     * Retrieves the updated list of Content from the backend and stores said content to
     * the Database.
     *
     * The observing Flow from [observeContentFromDb] will be updated and emits the
     * updated list of [GenreWithContent] to [ContentListFragment]
     *
     * If there are issues fetching the latest Content, the [LiveEvent] [contentFetchFailedEvent]
     * will be triggered, which notifies the user that the fetching has failed
     */
    fun getContent() = viewModelScope.launch {
        runCatching {
            repository.getContent()
        }.onFailure {
            _contentFetchFailedEvent.value = Unit
        }
    }

}