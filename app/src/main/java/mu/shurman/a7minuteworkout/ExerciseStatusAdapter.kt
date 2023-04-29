package mu.shurman.a7minuteworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import mu.shurman.a7minuteworkout.databinding.ItemExerciseStatusBinding

/**
 *@Created_by : @Muhammad Al-Shurman on 4/25/2023
 *@E-mail: muhd.shurman@gmail.com
 */
class ExerciseStatusAdapter(val  items:ArrayList<ExcerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder> (){
        class ViewHolder(binding : ItemExerciseStatusBinding):
            RecyclerView.ViewHolder(binding.root){
                val tvItem = binding.tvItem
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.
        inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model : ExcerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        when {
            model.getIsSelected()->{
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.item_circular_thin_color_accent_border)
                holder.tvItem.setTextColor(Color.parseColor("#FF9045"))

            }
            model.getIsCompleted()->{

                    holder.tvItem.background =
                        ContextCompat.getDrawable(holder.itemView.context,
                            R.drawable.item_circular_color_accent_background)
                    holder.tvItem.setTextColor(Color.parseColor("#FF9045"))
            }
            else ->{

                    holder.tvItem.background =
                        ContextCompat.getDrawable(holder.itemView.context,
                            R.drawable.item_circular_color_orange_background)
                    holder.tvItem.setTextColor(Color.parseColor("#FF9045"))
            }
        }
    }
}