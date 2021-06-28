package com.mahmoudshaaban.listmakers

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

   private lateinit var listsRecyclerView: RecyclerView
    var listDataManager : ListDataManager = ListDataManager(this)

    companion object {
     const val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val list = listDataManager.readLists()
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter = TodoListAdapter(list)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            showDialog()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showDialog() {
        val DialogTitle = "Create your Own Task"
        var PositiveButton = "Create"
        var myDialog = AlertDialog.Builder(this)
        val todoListEditText = EditText(this)
        todoListEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        myDialog.setTitle(DialogTitle)
        myDialog.setView(todoListEditText)
        myDialog.setPositiveButton(PositiveButton) { dialog, _ ->
            val adapter = listsRecyclerView.adapter as TodoListAdapter
            val list = TaskList(todoListEditText.text.toString())
            listDataManager.saveList(list)
            adapter.addlist(list)

          //  adapter.addItem(todoListEditText.text.toString())
            dialog.dismiss()
            showTaskListItmes(list)
        }
        myDialog.create().show()

    }

    fun showTaskListItmes(list : TaskList){
        val taskListItem = Intent(this , MainActivity2::class.java)
        // And unfortunately a task list isn't supported by putExtra.
        taskListItem.putExtra(INTENT_LIST_KEY , list)
        startActivity(taskListItem)
    }
}