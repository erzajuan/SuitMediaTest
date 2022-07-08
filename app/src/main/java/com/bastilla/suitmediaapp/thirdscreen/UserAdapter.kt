package com.bastilla.suitmediaapp.thirdscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bastilla.suitmediaapp.api.DataItem
import com.bastilla.suitmediaapp.databinding.ItemCardBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class UserAdapter(private val listUser: List<DataItem>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    inner class ViewHolder(var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = listUser[position]
        with(viewHolder.binding) {
            tvName.text = user.firstName + " " + user.lastName
            tvEmail.text = user.email
            Glide.with(root.context)
                .load(user.avatar)
                .transform(CircleCrop())
                .into(imgAvatar)
            root.setOnClickListener { onItemClickCallback?.onItemClicked((listUser[position])) }
        }
    }

    override fun getItemCount(): Int = listUser.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}