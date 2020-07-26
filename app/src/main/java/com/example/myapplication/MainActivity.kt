package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val exampleList = generateDummyList(500)

        //recycler_View.adapter = ExampleAdapter(exampleList)
        recycler_View.adapter = adapter
        recycler_View.layoutManager = LinearLayoutManager(this)
        recycler_View.setHasFixedSize(true)

    }
    fun insertItem(view: View){
        val index = Random.nextInt(8)

        val newItem = Exampleitem(
            R.drawable.ic_android,
            "New item at position $index",
            "Line 2"
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view: View){
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this,"Item $position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)

    }

    private fun generateDummyList(size: Int): ArrayList<Exampleitem> {
        val list = ArrayList<Exampleitem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }
            val item = Exampleitem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }


}