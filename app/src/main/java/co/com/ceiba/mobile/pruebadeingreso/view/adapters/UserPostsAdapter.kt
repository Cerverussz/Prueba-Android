package co.com.ceiba.mobile.pruebadeingreso.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserPosts
import kotlinx.android.synthetic.main.post_list_item.view.*

class UserPostsAdapter : RecyclerView.Adapter<UserPostsAdapter.ViewHolder>() {

    private var dataItems = mutableListOf<UserPosts>()

    fun setData(usersList: MutableList<UserPosts>) {
        dataItems.clear()
        dataItems = usersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userPosts = dataItems[position]
        holder.bind(userPosts)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userPosts: UserPosts) {
            itemView.title.text = userPosts.title
            itemView.body.text = userPosts.body
        }
    }
}

