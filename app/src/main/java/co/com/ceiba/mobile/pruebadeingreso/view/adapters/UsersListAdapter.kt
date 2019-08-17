package co.com.ceiba.mobile.pruebadeingreso.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.core.onClick
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.InfoUser
import kotlinx.android.synthetic.main.user_list_item.view.*

class UsersListAdapter(private val clickClosure: (InfoUser) -> Unit) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private var dataItems = mutableListOf<InfoUser>()

    fun setData(usersList: MutableList<InfoUser>) {
        dataItems.clear()
        dataItems = usersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usersList = dataItems[position]
        holder.bind(usersList)
        holder.bindClick(usersList)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usersList: InfoUser) {
            itemView.name.text = usersList.name
            itemView.phone.text = usersList.phone
            itemView.email.text = usersList.email
        }

        fun bindClick(usersList: InfoUser) {
            itemView.btn_view_post.onClick {
                clickClosure(usersList)
            }
        }
    }
}