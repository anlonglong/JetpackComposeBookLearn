package com.longlong.an.jetpackcomposebooklearn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.longlong.an.jetpackcomposebooklearn.DerivedStateOf.DerivedStateOfExampleActivity
import com.longlong.an.jetpackcomposebooklearn.chapter2.*
import com.longlong.an.jetpackcomposebooklearn.chapter4.*
import com.longlong.an.jetpackcomposebooklearn.chapter5.BottomNavigationActivity
import com.longlong.an.jetpackcomposebooklearn.chapter5.LazyColumnActivty
import com.longlong.an.jetpackcomposebooklearn.chapter5.MultiTypeActivity
import com.longlong.an.jetpackcomposebooklearn.chapter5.StickActivity
import com.longlong.an.jetpackcomposebooklearn.chapter6.CustomerView1Activity
import com.longlong.an.jetpackcomposebooklearn.chapter6.CustomerView2Activity
import com.longlong.an.jetpackcomposebooklearn.chapter7.Animation1Activity
import com.longlong.an.jetpackcomposebooklearn.chapter7.Animation2Activity
import com.longlong.an.jetpackcomposebooklearn.chapter7.CustomerAnimation1Activity
import com.longlong.an.jetpackcomposebooklearn.chapter7.GuestureActivity
import com.longlong.an.jetpackcomposebooklearn.chapter8.NavigationActivity
import com.longlong.an.jetpackcomposebooklearn.chapter8.ViewModelActivity
import com.longlong.an.jetpackcomposebooklearn.chapter9.AndroidViewActivity
import com.longlong.an.jetpackcomposebooklearn.chapter9.FragmentActivity
import com.longlong.an.jetpackcomposebooklearn.chapter9.ViewBindingActivity
import com.longlong.an.jetpackcomposebooklearn.effect.EffectActivity
import com.longlong.an.jetpackcomposebooklearn.localcomposiprovider.CompositionLocalProviderActivity
import com.longlong.an.jetpackcomposebooklearn.mediataton.MediationActivity
import com.longlong.an.jetpackcomposebooklearn.modify.ModifierActivity
import com.longlong.an.jetpackcomposebooklearn.snapshotFlow.SnapFlowActivity
import com.longlong.an.jetpackcomposebooklearn.state.MutableStateActivity

class CategoryActivity : AppCompatActivity() {

    private val recycle: RecyclerView by lazy {
        findViewById(R.id.recycle_view)
    }

    private val recycleData: MutableList<ItemInfo> by lazy {
        mutableListOf(
            ItemInfo("Main", this.baseContext, MainActivity::class.java),
            ItemInfo("主题", this.baseContext, ThemeActivity::class.java),
            ItemInfo("Text 的属性使用", this.application, TextDisplayActivity::class.java),
            ItemInfo(
                "TextField/OutlinedTextField 的属性使用",
                this.application,
                TextFieldActivity::class.java
            ),
            ItemInfo("Button 的属性使用", this.application, ButtonActivity::class.java),
            ItemInfo("Image 的属性使用", this.application, ImageViewActivity::class.java),
            ItemInfo("Progress 的属性使用", this.application, ProgressBarActivity::class.java),
            ItemInfo("Column布局", this.application, ColumnActivity::class.java),
            ItemInfo("Row布局", this.application, RowActivity::class.java),
            ItemInfo("Box布局", this.application, BoxActivity::class.java),
            ItemInfo("Modify布局", this.application, ModifierActivity::class.java),
            ItemInfo("Scaffold布局", this.application, ScaffoldActivity::class.java),
            ItemInfo("Constraint布局", this.application, ConstranitActivity::class.java),
            ItemInfo("LazyColumn布局", this.application, LazyColumnActivty::class.java),
            ItemInfo("MultiLazyColumn布局", this.application, MultiTypeActivity::class.java),
            ItemInfo("StickLazyColumn布局", this.application, StickActivity::class.java),
            ItemInfo("Bottom navi 布局", this.application, BottomNavigationActivity::class.java),
            ItemInfo("Customer view 1", this.application, CustomerView1Activity::class.java),
            ItemInfo("Customer view 2 矩形｜椭圆", this.application, CustomerView2Activity::class.java),
            ItemInfo("animation", this.application, Animation1Activity::class.java),
            ItemInfo("animation2", this.application, Animation2Activity::class.java),
            ItemInfo("animation2", this.application, CustomerAnimation1Activity::class.java),
            ItemInfo("Gestures", this.application, GuestureActivity::class.java),
            ItemInfo("VM1", this.application, ViewModelActivity::class.java),
            ItemInfo("Nav", this.application, NavigationActivity::class.java),
            ItemInfo("代码创建 Android View", this.application, AndroidViewActivity::class.java),
            ItemInfo("compose中使用Android的布局中的view", this.application, ViewBindingActivity::class.java),
            ItemInfo("fragment in compose activity", this.application, FragmentActivity::class.java),
            ItemInfo("Mediation activity", this.application, MediationActivity::class.java),
            ItemInfo("解释说明 compose中的 remember 和 state的含义", this.application, MutableStateActivity::class.java),
            ItemInfo("附带效应说明", this.application, EffectActivity::class.java),
            ItemInfo("ComposeLocalOf使用", this.application, CompositionLocalProviderActivity::class.java),
            ItemInfo("SnapFlow使用", this.application, SnapFlowActivity::class.java),
            ItemInfo("DerivedStateOf使用", this.application, DerivedStateOfExampleActivity::class.java),
            ItemInfo("Modifier heightIn/widthIn/sizeIn 属性的区别 ", this.application, ModifierActivity::class.java),
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        recycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycle.addItemDecoration(
            DividerItemDecoration(
                application,
                DividerItemDecoration.VERTICAL
            )
        )
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
                vh.itemView.setOnClickListener {
                    val itemInfo = this.data[vh.adapterPosition]
                    Result.runCatching {
                        //Toast.makeText(this@CategoryActivity, "lllllll", Toast.LENGTH_SHORT).show()
                        parent.context.startActivity(Intent(itemInfo.ctx, itemInfo.java))
                    }
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

    inline fun <reified T : Activity> Context.starterSafely() {
        Result.runCatching {
            this@starterSafely.startActivity(Intent(this@starterSafely, T::class.java))
        }

    }
}