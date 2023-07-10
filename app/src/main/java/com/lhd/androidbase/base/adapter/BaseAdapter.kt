import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VB : ViewDataBinding> : RecyclerView.Adapter<BaseAdapter<T, VB>.ViewHolder>() {

    protected var items: List<T> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VB>(inflater, getLayoutResId(), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        bind(holder.binding, item)
        holder.binding.executePendingBindings()
    }

    protected abstract fun getLayoutResId(): Int

    protected abstract fun bind(binding: VB, item: T)

    @SuppressLint("NotifyDataSetChanged")
    @JvmName("setItems1")
    fun setItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)
}
