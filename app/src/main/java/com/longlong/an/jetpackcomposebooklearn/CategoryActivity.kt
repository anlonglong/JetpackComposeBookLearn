package com.longlong.an.jetpackcomposebooklearn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.longlong.an.jetpackcomposebooklearn.chapter2.ButtonActivity
import com.longlong.an.jetpackcomposebooklearn.chapter2.TextDisplayActivity
import com.longlong.an.jetpackcomposebooklearn.chapter2.TextFieldActivity
import com.longlong.an.jetpackcomposebooklearn.chapter2.ThemeActivity

class CategoryActivity : AppCompatActivity() {

    private val recycle: RecyclerView by lazy {
        findViewById(R.id.recycle_view)
    }

    private val recycleData: MutableList<ItemInfo> by lazy {
        mutableListOf(
            ItemInfo("Main",this.baseContext,MainActivity::class.java),
            ItemInfo("主题",this.baseContext,ThemeActivity::class.java),
            ItemInfo("Text 的属性使用",this.application, TextDisplayActivity::class.java),
            ItemInfo("TextField/OutlinedTextField 的属性使用",this.application, TextFieldActivity::class.java),
            ItemInfo("Button 的属性使用",this.application, ButtonActivity::class.java),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        recycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycle.addItemDecoration(DividerItemDecoration(application, DividerItemDecoration.VERTICAL))
        recycle.adapter = CategoryAdapter(recycleData)
    }

    inner class CategoryAdapter(private val data: MutableList<ItemInfo>) :
        RecyclerView.Adapter<CategoryViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            return CategoryViewHolder(
                View.inflate(
                    parent.context,
                    R.layout.category_item,
                    null
                )
            ).also { vh ->
                vh.itemText.setOnClickListener {
                    val itemInfo = this.data[vh.adapterPosition]
                    parent.context.startActivity(Intent(itemInfo.ctx, itemInfo.java))
                }
            }
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.itemText.text = data[position].desc
        }

        override fun getItemCount(): Int {
            return data.size
        }

    }

    inner class CategoryViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.tv)

    }

    data class ItemInfo(val desc: String, val ctx: Context, val java: Class<out Any>)
}