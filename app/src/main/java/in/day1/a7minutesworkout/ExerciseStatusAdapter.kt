package `in`.day1.a7minutesworkout

import `in`.day1.a7minutesworkout.databinding.ItemExerciseStatusBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

        inner class ViewHolder(binding: ItemExerciseStatusBinding):
            RecyclerView.ViewHolder(binding.root) {
            val tvItem = binding.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Attach the id to the holder as the string
        val model = items[position]
        holder.tvItem.text = model.getId().toString()

        if(model.getSelectedStatus()) {
            holder.tvItem.background = ContextCompat.getDrawable(
                holder.itemView.context,R.drawable.item_selected_border_blue)
        }

        if (model.getCompletedStatus()) {

            holder.tvItem.background = ContextCompat.getDrawable(
                holder.itemView.context,R.drawable.item_completed)
        }
        }


    override fun getItemCount(): Int {
        return items.size
    }
}