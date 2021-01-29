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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ListThingsBGGViewModel(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    application: Application,
    val appDatabase: AppDatabase,
    val dataProvider: DataProvider
) :
    BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = ListThingsBGGViewModel::class.qualifiedName

    private val thingBGGMapperBBDD = ThingBGGMapperBBDD()

    private val listAllThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listBoardGamesThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()
    private val listExpansionsThingBGGModel: ArrayList<ThingBGGModel> = ArrayList()

    val listShowThingBGGModelMutableLiveData: MutableLiveData<ArrayList<ThingBGGModel>> = MutableLiveData()
    val totalBoardGamesMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val totalBoardGamesBasicMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val totalBoardGamesExpansionMutableLiveData: MutableLiveData<Int> = MutableLiveData()
    val typeShowConfigSelectedListMutableLiveData: MutableLiveData<TypeThingBGG> = MutableLiveData()

    var userSelectedBGG: String = ""

    fun observeThingsUser(lifecycleOwner: LifecycleOwner, userSelectedBGG: String) {
        if (this.userSelectedBGG != userSelectedBGG) {
            this.userSelectedBGG = userSelectedBGG

            appDatabase.JoinsBGGDao().getAllThingsUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThings ->
                listAllThingBGGModel.clear()
                listAllThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThings))
                totalBoardGamesMutableLiveData.postValue(listThings.size)
                if (typeShowConfigSelectedListMutableLiveData.value == TYPE_THING_UNKNOW) {
                    showList(TYPE_THING_UNKNOW)
                }
            })

            appDatabase.JoinsBGGDao().getAllBoardGameUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThingsBoardgame ->
                listBoardGamesThingBGGModel.clear()
                listBoardGamesThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThingsBoardgame))
                totalBoardGamesBasicMutableLiveData.postValue(listThingsBoardgame.size)
                if (typeShowConfigSelectedListMutableLiveData.value == TYPE_THING_BOARDGAME || typeShowConfigSelectedListMutableLiveData.value == null) {
                    showList(TYPE_THING_BOARDGAME)
                }
            })

            appDatabase.JoinsBGGDao().getAllExpansionUserLiveData(userSelectedBGG).observe(lifecycleOwner, { listThingsExpansion ->
                listExpansionsThingBGGModel.clear()
                listExpansionsThingBGGModel.addAll(thingBGGMapperBBDD.toModelListThingsEntity(listThingsExpansion))
                totalBoardGamesExpansionMutableLiveData.postValue(listThingsExpansion.size)
                if (typeShowConfigSelectedListMutableLiveData.value == TYPE_THING_EXPANSION) {
                    showList(TYPE_THING_EXPANSION)
                }
            })
        }
    }

    fun showList(typeShowList: TypeThingBGG) {
        typeShowConfigSelectedListMutableLiveData.value = typeShowList
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
}