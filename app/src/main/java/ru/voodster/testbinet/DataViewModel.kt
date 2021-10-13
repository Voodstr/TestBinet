package ru.voodster.testbinet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import ru.voodster.testbinet.api.ItemModel
import ru.voodster.testbinet.ext.Event

class DataViewModel : ViewModel() {

    private val component = DaggerViewModelComponent.builder().build()
    private val api = component.api()

    private var session: String = ""

    private val fakeItem = ItemModel("4klJeiCKTs", "Вторая запись", 1442236233, 1442236233)
    private val fakeList = arrayListOf(fakeItem, fakeItem, fakeItem, fakeItem)

    private val itemsListLiveData = MutableLiveData<List<ItemModel>>()
    val items: LiveData<List<ItemModel>>
        get() = itemsListLiveData

    private val infoLiveData = MutableLiveData<ItemModel>()
    val info: LiveData<ItemModel>
        get() = infoLiveData


    private val _errorMsg = MutableLiveData<Event<String>>()
    val errorMsg: LiveData<Event<String>>
        get() = _errorMsg


    fun getData() {
        val str = "a=get_entries&session=$session"
        //val str = "a=get_entries&session=nukETXdbjYoUkoOKMH"
        val body = str.toRequestBody("application/x-www-form-urlencoded".toMediaType())
        api.getEntries(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                if (!result.data.isNullOrEmpty()){
                    itemsListLiveData.postValue(result.data[0])
                }
            }, { error ->
                Log.d("getData", error.localizedMessage?: "Unknown error")
                _errorMsg.value = Event(error.localizedMessage ?: "Unknown error")
            })
        //itemsListLiveData.postValue(fakeList)
    }


    fun startNewSession() {
        val str = "a=new_session"
        val body = str.toRequestBody("application/x-www-form-urlencoded".toMediaType())
        api.newSession(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                session = result.data.session
            }, { error ->
                Log.d("startNewSession", "${error.localizedMessage}")
                _errorMsg.value = Event(error.localizedMessage ?: "Unknown error")
            })
    }


    fun addEntry(record:String){
        if (record!="null"){
            val str = "a=add_entry&session=$session&body=$record"
            //val str = "a=add_entry&session=nukETXdbjYoUkoOKMH&body=$record"
            val body = RequestBody.create("application/x-www-form-urlencoded".toMediaType(),str)
            api.addEntry(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { error ->
                    Log.d("startNewSession", "${error.localizedMessage}")
                    _errorMsg.value = Event(error.localizedMessage ?: "Unknown error")
                })
        }
    }
}