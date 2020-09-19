package com.kacper.mykanban.data

import android.content.Context
import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kacper.mykanban.getOrAwaitValue
import com.kacper.mykanban.ui.main.dummy.DummyContent
import com.kacper.mykanban.utilities.TO_DO
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class KanbanCardDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var kanbanCardDao: KanbanCardDao
    private lateinit var context: Context

    @Before
    fun createAppDatabase() = runBlocking{
        context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        kanbanCardDao = appDatabase.kanbanCardDao()
        kanbanCardDao.insertAll(DummyContent.ITEMS)

    }

    @After
    fun closeAppDatabase() {
        appDatabase.close()
    }

    @Test
    fun testGetKanbanCard(){
        val tmp = 10
        val actual = kanbanCardDao.getKanbanCard(tmp).getOrAwaitValue {  }
        val expected = DummyContent.ITEMS[tmp - 1]
        assertThat(actual, equalTo(expected))
    }

    @Test
    fun testGetAll(){
        val list = kanbanCardDao.getAll().getOrAwaitValue {  }
        assertThat(list.size, equalTo(DummyContent.COUNT))
    }

    @Test
    fun testDeleteAll() = runBlocking {
        var list = kanbanCardDao.getAll().getOrAwaitValue {  }
        assertThat(list.size, equalTo(DummyContent.COUNT))
        kanbanCardDao.deleteAll()
        list = kanbanCardDao.getAll().getOrAwaitValue {  }
        assertThat(list.size, equalTo(0))
    }
}