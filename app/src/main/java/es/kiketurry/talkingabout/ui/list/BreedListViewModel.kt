package es.kiketurry.talkingabout.ui.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class BreedListViewModel(application: Application, dataProvider: DataProvider) : BaseViewModel(application) {
    override val TAG: String? get() = BreedListViewModel::class.qualifiedName

    fun getNamesBreedList(breedModelList: ArrayList<BreedModel>): java.util.ArrayList<String> {
        var namesBreedList: java.util.ArrayList<String> = java.util.ArrayList()
        for (breedModel in breedModelList) {
            namesBreedList.add(breedModel.name)
        }
        return namesBreedList
    }
}