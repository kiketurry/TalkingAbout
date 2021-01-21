package es.kiketurry.talkingabout.ui.bgg.listthings

import android.app.Application
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ListUserBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class ListThingsBGGViewModel(application: Application, var appDatabase: AppDatabase, val dataProvider: DataProvider) :
    BaseViewModel(application) {

    override val TAG: String? get() = ListThingsBGGViewModel::class.qualifiedName

    val listThingsBGGMapperBBDD = ListUserBGGMapperBBDD()
    val thingsBGGMapperBBDD = ThingBGGMapperBBDD()

    fun userSelectedBGG(userSelectedBGG: String) {
//        getListAndBoardGamesUser(userSelectedBGG)
    }


}