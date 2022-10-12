package com.miladsh7.mytodolist.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miladsh7.mytodolist.utils.TABLE_TODO
import com.miladsh7.mytodolist.view.detail.Selection

@kotlinx.parcelize.Parcelize
@Entity(tableName = TABLE_TODO)
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var desc: String? = null,
    var calendar: String? = null,
    var selectionId: Int = Selection.BLUE.ordinal
) : Parcelable
