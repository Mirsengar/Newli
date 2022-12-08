package contagiouscode.mirsengar.newli.presentation.ui.screen.details

import androidx.lifecycle.ViewModel
import contagiouscode.mirsengar.newli.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
          private val repository : NewsRepository ,
) : ViewModel() {}