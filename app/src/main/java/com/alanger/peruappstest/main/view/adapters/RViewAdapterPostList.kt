package com.alanger.peruappstest.main.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

import com.alanger.peruappstest.R
import com.alanger.peruappstest.models.VO.PostVO


class RViewAdapterPostList(private val dataList: List<PostVO>) : RecyclerView.Adapter<RViewAdapterPostList.ViewHolder>(), View.OnClickListener {

    private var onClickListener: View.OnClickListener? = null


    internal val maxiCharactersTittle = 80
    internal val maxiCharactersBody = 200

    override fun onClick(v: View) {
        if (onClickListener != null) {
            onClickListener!!.onClick(v)
        }
    }


    inner class ViewHolder
    //FloatingActionButton fpli_fabSave;

    (val myView: View) : RecyclerView.ViewHolder(myView) {

        internal var fpli_tViewTittle: TextView
        internal var fpli_tViewBody: TextView

        init {

            fpli_tViewTittle = myView.findViewById(R.id.fpli_tViewTittle)
            fpli_tViewBody = myView.findViewById(R.id.fpli_tViewBody)
            //  fpli_fabSave= myView.findViewById(R.id.fpli_fabSave);
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_post_list_item, null, false)

        view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.setOnClickListener(this)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        if (item.title.length > maxiCharactersTittle) {
            holder.fpli_tViewTittle.text = item.title.substring(0, maxiCharactersTittle) + "..." // mostrar el recorte del body
        } else {
            holder.fpli_tViewTittle.text = item.title
        }


        if (item.body.length > maxiCharactersBody) {
            holder.fpli_tViewBody.text = item.body.substring(0, maxiCharactersBody) + "..." // mostrar el recorte del body
        } else {
            holder.fpli_tViewBody.text = item.body
        }
    }


    fun setOnClicListener(listener: View.OnClickListener) {
        this.onClickListener = listener

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
