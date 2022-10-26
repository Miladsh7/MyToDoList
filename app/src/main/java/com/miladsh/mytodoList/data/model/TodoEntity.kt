package com.miladsh.mytodoList.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miladsh.mytodoList.utils.TABLE_TODO
import com.miladsh.mytodoList.view.detail.Selection

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
