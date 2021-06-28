package com.mahmoudshaaban.listmakers

import android.os.Parcel
import android.os.Parcelable

class TaskList(val name : String , val tasks : ArrayList<String> = ArrayList()) : Parcelable {

    constructor(parcel : Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    companion object CREATOR : Parcelable.Creator<TaskList>{
        override fun createFromParcel(source: Parcel): TaskList {
            return TaskList(source)
        }

        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)

    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        // here we're converting our tasklist into a parcel
        dest.writeString(name)
        dest.writeStringList(tasks)
    }

    override fun describeContents() = 0


}