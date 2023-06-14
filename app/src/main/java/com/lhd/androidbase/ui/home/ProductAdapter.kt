import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lhd.androidbase.data.modelJson.ResProductJson
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.databinding.ItemProductBinding

class ProductAdapter(private var lsProduct: List<Product> = ArrayList()) :
    RecyclerView.Adapter<ProductAdapter.ItemView>() {

    private lateinit var listener: OnClickItem

    class ItemView(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Product>) {
        lsProduct = list
        notifyDataSetChanged()
    }

    fun setOnClickItem(listener: OnClickItem) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return lsProduct.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val item = lsProduct[position]
        holder.binding.product = item
        holder.binding.itemProduct.setOnClickListener{
            this.listener.clickItem(item)
        }
    }

    interface OnClickItem {
        fun clickItem(results: Product);
    }

}