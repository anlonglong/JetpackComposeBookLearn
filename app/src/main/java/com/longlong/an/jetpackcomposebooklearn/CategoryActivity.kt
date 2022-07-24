package com.longlong.an.jetpackcomposebooklearn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.longlong.an.jetpackcomposebooklearn.chapter2.*
import com.longlong.an.jetpackcomposebooklearn.chapter4.*
import com.longlong.an.jetpackcomposebooklearn.chapter5.LazyColumnActivty
import com.longlong.an.jetpackcomposebooklearn.chapter5.MultiTypeActivity

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
            ItemInfo("Image 的属性使用",this.application, ImageViewActivity::class.java),
            ItemInfo("Progress 的属性使用",this.application, ProgressBarActivity::class.java),
            ItemInfo("Column布局",this.application, ColumnActivity::class.java),
            ItemInfo("Row布局",this.application, RowActivity::class.java),
            ItemInfo("Box布局",this.application, BoxActivity::class.java),
            ItemInfo("Modify布局",this.application, ModifyActivity::class.java),
            ItemInfo("Scaffold布局",this.application, ScaffoldActivity::class.java),
            ItemInfo("Constraint布局",this.application, ConstranitActivity::class.java),
            ItemInfo("LazyColumn布局",this.application, LazyColumnActivty::class.java),
            ItemInfo("MultiLazyColumn布局",this.application, MultiTypeActivity::class.java),

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

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.tv)

    }

    data class ItemInfo(val desc: String, val ctx: Context, val java: Class<out Any>)

    inline fun <reified T : Activity> Context.starter(){
        this.startActivity(Intent(this, T::class.java))
    }
}