package com.example.musicplayerdemo.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayerdemo.R
import com.example.musicplayerdemo.data.LyricsInfo
import com.example.musicplayerdemo.viewModel.MusicViewModel
import kotlinx.android.synthetic.main.lyrics_item.view.*

class LyricAdapter(
    private val activity: Activity,
    private val viewModel: MusicViewModel): RecyclerView.Adapter<LyricAdapter.ViewHolder>() {

    private val list = ArrayList<LyricsInfo>()

    private var currentPosition = 0

    private var isToggleOn = false

    companion object {
        private const val colorWhite = R.color.colorWhite
        private const val colorPreview = R.color.colorPreview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.lyrics_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time = list[position].time
        val lyric = list[position].text
        holder.item.text = lyric

        if (position == currentPosition) setTextColor(holder.item, colorWhite)
        else setTextColor(holder.item, colorPreview)

        holder.item.setOnClickListener {
            goToSelectedTime(time)
        }
    }

    private fun goToSelectedTime(time: Long) {
        viewModel.selectLyricPosition(time)
    }

    private fun setTextColor(tv: AppCompatTextView, color: Int) {
        tv.setTextColor(getColor(activity, color))
    }

    internal fun addList(data: List<LyricsInfo>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    internal fun setCurrentPosition(position: Int) {
        currentPosition = position
        notifyDataSetChanged()
    }

    internal fun setToggleValue(isOn: Boolean) {
        isToggleOn = isOn
    }

    inner class ViewHolder(containerView: View): RecyclerView.ViewHolder(containerView) {
        val item: AppCompatTextView = containerView.tv_lyrics_item
    }
}