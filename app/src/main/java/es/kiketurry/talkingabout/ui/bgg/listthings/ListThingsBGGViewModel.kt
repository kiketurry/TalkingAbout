package es.kiketurry.talkingabout.ui.bgg.listthings

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.*
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class ListThingsBGGViewModel(application: Application, var appDatabase: AppDatabase, val dataProvider: DataProvider) :
    BaseViewModel(application) {

    override val TAG: String? get() = ListThingsBGGViewModel::class.qualifiedName

    private val thingBGGMapperBBDD = ThingBGGMapperBBDD()

    private val listAllThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listBoardGamesThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listExpansionsThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()

    val listShowThingBGGModelMutableLiveData: MutableLiveData<ArrayList<ThingBGGModel>> = MutableLiveData()
    val listTotalBoardGamesMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val listTotalBoardGamesBasicMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val listTotalBoardGamesExpansionMutableLiveData: MutableLiveData<Int> = MutableLiveData()

    var typeShowList: TypeThingBGG = TYPE_THING_BOARDGAME

    fun observeThingsUser(lifecycleOwner: LifecycleOwner, userSelectedBGG: String) {
        appDatabase.JoinsBGGDao().getAllThingsUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThings ->
            listAllThingBGGModel.clear()
            listAllThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThings))
            listTotalBoardGamesMutableLiveData.postValue(listThings.size)
            if (typeShowList == TYPE_THING_UNKNOW) {
                checkTypeShowList()
            }
        })

        appDatabase.JoinsBGGDao().getAllThingsBoardGameUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThingsBoardgame ->
            listBoardGamesThingBGGModel.clear()
            listBoardGamesThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThingsBoardgame))
            listTotalBoardGamesBasicMutableLiveData.postValue(listThingsBoardgame.size)
            if (typeShowList == TYPE_THING_BOARDGAME) {
                checkTypeShowList()
            }
        })

        appDatabase.JoinsBGGDao().getAllThingsExpansionUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThingsExpansion ->
            listExpansionsThingBGGModel.clear()
            listExpansionsThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThingsExpansion))
            listTotalBoardGamesExpansionMutableLiveData.postValue(listThingsExpansion.size)
            if (typeShowList == TYPE_THING_EXPANSION) {
                checkTypeShowList()
            }
        })
    }

    private fun checkTypeShowList() {
        when (typeShowList) {
            TYPE_THING_UNKNOW -> {
                listShowThingBGGModelMutableLiveData.postValue(listAllThingBGGModel)
            }
            TYPE_THING_BOARDGAME -> {
                listShowThingBGGModelMutableLiveData.postValue(listBoardGamesThingBGGModel)
            }
            TYPE_THING_EXPANSION -> {
                listShowThingBGGModelMutableLiveData.postValue(listExpansionsThingBGGModel)
            }
        }
    }

    fun showList(typeShowList: TypeThingBGG) {
        this.typeShowList = typeShowList
        checkTypeShowList()
    }
}