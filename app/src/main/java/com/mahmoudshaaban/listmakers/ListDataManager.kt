package com.mahmoudshaaban.listmakers

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager (private val context: Context){
    // shared prefs does not save arraylists it save sets

    fun saveList(list : TaskList){
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(list.name , list.tasks.toHashSet())
        sharedPrefs.apply()

    }

    fun readLists(): ArrayList<TaskList>{
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        // The contents contain a map of keys and values.
        val contents = sharedPrefs.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in contents) {
            // first we get a hashset and convert it to an arraylist
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            // then we create a tasklist from it
            val list = TaskList(taskList.key, taskItems)
            // and finally we add it to the array
            taskLists.add(list)
        }

        return taskLists

    }

}