package tw.edu.ncku.iim.rsliu.setcard.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import tw.edu.ncku.iim.rsliu.setcard.CardsData
import tw.edu.ncku.iim.rsliu.setcard.R
import tw.edu.ncku.iim.rsliu.setcard.SetCardView
import tw.edu.ncku.iim.rsliu.setcard.SharedViewModel

class ResultGridRecyclerViewAdapter(
    private val sharedViewModel: SharedViewModel,
) : RecyclerView.Adapter<ResultGridRecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val cardView: CardView = itemView.findViewById(R.id.cardView)
        val cardView1: SetCardView = itemView.findViewById(R.id.setCardView1)
        val cardView2: SetCardView = itemView.findViewById(R.id.setCardView2)
        val cardView3: SetCardView = itemView.findViewById(R.id.setCardView3)
    }

    //private val historyLiveData: MutableList<MutableList<CardsData>> = sharedViewModel.history

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i("history size", sharedViewModel.history.size.toString())
        Log.i("backgroundColor size", sharedViewModel.backgroundColor.size.toString())

        return sharedViewModel.history.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cardView1 = holder.cardView1
        val cardView2 = holder.cardView2
        val cardView3 = holder.cardView3

        cardView1.color = sharedViewModel.history[position][0].cardColor
        cardView1.shapeCount = sharedViewModel.history[position][0].shapeCount
        cardView1.shape = sharedViewModel.history[position][0].shape
        cardView1.shading = sharedViewModel.history[position][0].shading
        //cardView1.cardBackgroundColor = sharedViewModel.backgroundColor[position][0]

        cardView2.color = sharedViewModel.history[position][1].cardColor
        cardView2.shapeCount = sharedViewModel.history[position][1].shapeCount
        cardView2.shape = sharedViewModel.history[position][1].shape
        cardView2.shading = sharedViewModel.history[position][1].shading
        //cardView2.cardBackgroundColor = sharedViewModel.backgroundColor[position][1]

        cardView3.color = sharedViewModel.history[position][2].cardColor
        cardView3.shapeCount = sharedViewModel.history[position][2].shapeCount
        cardView3.shape = sharedViewModel.history[position][2].shape
        cardView3.shading = sharedViewModel.history[position][2].shading
        //cardView3.cardBackgroundColor = sharedViewModel.backgroundColor[position][2]
    }

    private inner class MyObserver : Observer<List<MutableList<CardsData>>> {
        override fun onChanged(history: List<MutableList<CardsData>>?) {
            Log.i("history size", sharedViewModel.history.size.toString())
            Log.i("backgroundColor size", sharedViewModel.backgroundColor.size.toString())

            notifyDataSetChanged()
        }
    }
}

