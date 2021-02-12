package es.kiketurry.talkingabout.data.repository.bbdd.users

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BBDDUserBGGRoomEntityTest {

    private lateinit var userBGGDao: UserBGGDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        userBGGDao = db.UserBGGDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserBGGAndReadTest() {
        val userEntity = UserBGGRoomEntity("raymon", "Ramón", "r@gmail.com", "34", "685274193")
        runBlocking {
            userBGGDao.insert(userEntity)
            val findByUserBGG = userBGGDao.findByUserBGG("raymon")
            MatcherAssert.assertThat(findByUserBGG, CoreMatchers.equalTo(userEntity))
        }
    }

}