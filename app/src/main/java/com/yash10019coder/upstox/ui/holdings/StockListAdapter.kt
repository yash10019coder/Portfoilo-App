package com.yash10019coder.upstox.ui.holdings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yash10019coder.upstox.databinding.ItemListStockViewBinding
import com.yash10019coder.upstox.ui.databinding.StockListingItemModel
import timber.log.Timber

class StockListAdapter(
    private val context: Context,
    private val listingItemModels: MutableList<StockListingItemModel>,
    private val onItemClick: (StockListingItemModel) -> Unit
) : RecyclerView.Adapter<StockListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListStockViewBinding =
            ItemListStockViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("StockListAdapter: onBindViewHolder itemsSize %s", listingItemModels.size)
        holder.bind(listingItemModels[position])
        holder.onClick(listingItemModels[position])
    }

    override fun getItemCount(): Int {
        return listingItemModels.size
    }

    fun updateList(list: List<StockListingItemModel>) {
        listingItemModels.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemListStockViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeSelectModel: StockListingItemModel) {
            binding.stock = employeeSelectModel
        }

        fun onClick(employeeListModel: StockListingItemModel) {
            itemView.setOnClickListener {
                onItemClick(employeeListModel)
            }
        }
    }
}
