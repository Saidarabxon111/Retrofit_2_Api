package uz.saidarabxon.retrofit_2_api.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.saidarabxon.retrofit_2_api.databinding.ItemRvBinding
import uz.saidarabxon.retrofit_2_api.models.MyTodoResponse

class RvAdapter(var list: List<MyTodoResponse>) : RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myTodoResponse: MyTodoResponse, position: Int ){

            itemRvBinding.tvName.text =myTodoResponse.sarlavha
            itemRvBinding.tvText.text =myTodoResponse.matn
            itemRvBinding.tvDate.text =myTodoResponse.oxirgi_muddat
            itemRvBinding.tvHolat.text =myTodoResponse.holat

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}