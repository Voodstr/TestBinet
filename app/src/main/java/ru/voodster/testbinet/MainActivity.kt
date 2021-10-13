package ru.voodster.testbinet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.voodster.testbinet.api.ItemModel
import ru.voodster.testbinet.add.AddFragment
import ru.voodster.testbinet.info.InfoFragment
import ru.voodster.testbinet.list.ListFragment

class MainActivity : AppCompatActivity(),ListFragment.OnItemClickListener {

    private val viewModel:DataViewModel by viewModels()

    lateinit var bottomNav :BottomNavigationView

    private val listFragment = ListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.startNewSession()
        openList()

        viewModel.info.observe(this,{
            openInfo(it)
        })

        viewModel.errorMsg.observe(this, { error ->
            error.getContentIfNotHandled()?.let {
                val updateDialogBuilder = AlertDialog.Builder(this)
                updateDialogBuilder.setTitle(R.string.ConnectionDialogTitle)
                updateDialogBuilder.setMessage(R.string.ConnectionDialogText)
                updateDialogBuilder.setCancelable(false)
                updateDialogBuilder.setPositiveButton(R.string.updateBtn
                ) { _, _ ->
                    viewModel.getData()
                }
                val b = updateDialogBuilder.create()
                b.show()
            }
        })

        setClickListeners()
        bottomNav = findViewById(R.id.bottomNavigation)
        setNavigationBar()
    }


    private fun setNavigationBar() = bottomNav.setOnItemSelectedListener {
        when(it.itemId){
            R.id.nav_list -> openList()
            R.id.nav_add -> openAdd()
        }
        true
    }

    private fun openInfo(item:ItemModel){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_toptobottom,R.anim.exit_bottomtotop,R.anim.enter_bottomtotop,R.anim.exit_toptobottom)
            .replace(R.id.fragmentContainer, InfoFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

    private fun openList(){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_toleftt,R.anim.exit_left_toright)
            .replace(R.id.fragmentContainer, ListFragment.newInstance().apply {
                listener = this@MainActivity
            })
            .commit()
    }
    private fun openAdd(){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_left_toright,R.anim.exit_right_toleft)
            .replace(R.id.fragmentContainer, AddFragment.newInstance())
            .commit()
    }

    private fun setClickListeners(){
        listFragment.listener = this
    }


    override fun onItemClick(item: ItemModel) {
        openInfo(item)
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }else{
            val exitDialogBuilder = AlertDialog.Builder(this)
            exitDialogBuilder.setTitle(R.string.exitDialogTitle)
            exitDialogBuilder.setMessage(R.string.exitDialogText)
            exitDialogBuilder.setCancelable(true)
            exitDialogBuilder.setPositiveButton(R.string.yesBtn
            ) { _, _ ->
                super.onBackPressed()
            }
            val b = exitDialogBuilder.create()
            b.show()
        }

    }


}