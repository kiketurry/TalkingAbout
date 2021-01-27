package es.kiketurry.talkingabout.ui.bgg.listthings

import android.app.Application
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.runBlocking

class ListThingsBGGViewModel(application: Application, var appDatabase: AppDatabase, val dataProvider: DataProvider) :
    BaseViewModel(application) {

    override val TAG: String? get() = ListThingsBGGViewModel::class.qualifiedName

    private val listAllThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listBoardGamesThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listExpansionsThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    val listShowThingBGGModelMutableLiveData: MutableLiveData<ArrayList<ThingBGGModel>> = MutableLiveData()

    fun userSelectedBGG(userSelectedBGG: String) {
        getThingsUser(userSelectedBGG)
    }

    private fun getThingsUser(userSelectedBGG: String) {
        runBlocking {
            listAllThingBGGModel.clear()
            listBoardGamesThingBGGModel.clear()
            listExpansionsThingBGGModel.clear()

            listAllThingBGGModel.addAll(
                ThingBGGMapperBBDD().toModelListThingsEntity(
                    appDatabase.JoinsBGGDao().getAllThingsUser(userSelectedBGG)
                )
            )

            listAllThingBGGModel.forEach { thingBGGModel ->
                when (thingBGGModel.type) {
                    ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW -> {
                    }
                    ThingBGGModel.TypeThingBGG.TYPE_THING_BOARDGAME -> {
                        listBoardGamesThingBGGModel.add(thingBGGModel)
                    }
                    ThingBGGModel.TypeThingBGG.TYPE_THING_EXPANSION -> {
                        listExpansionsThingBGGModel.add(thingBGGModel)
                    }
                }
            }
            listShowThingBGGModelMutableLiveData.postValue(listAllThingBGGModel)
        }
    }
}