package es.kiketurry.talkingabout.ui.cats.list

import android.app.Application
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class BreedListViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val dataProvider: DataProvider) :
    BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = BreedListViewModel::class.qualifiedName

    fun getNamesBreedList(breedModelList: ArrayList<BreedModel>): java.util.ArrayList<String> {
        var namesBreedList: java.util.ArrayList<String> = java.util.ArrayList()
        for (breedModel in breedModelList) {
            namesBreedList.add(breedModel.name)
        }
        return namesBreedList
    }
}