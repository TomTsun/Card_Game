package tw.edu.ncku.iim.rsliu.setcard.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.ncku.iim.rsliu.setcard.R
import tw.edu.ncku.iim.rsliu.setcard.SetCardView
import tw.edu.ncku.iim.rsliu.setcard.SharedViewModel

class CardGridRecyclerViewAdapter(
    private val sharedViewModel: SharedViewModel
) : RecyclerView.Adapter<CardGridRecyclerViewAdapter.MyViewHolder>() {

    interface SetCardViewClickListener {
        fun onSetCardViewClick(position: Int, cardView: SetCardView)
    }
    private var setCardViewClickListener: SetCardViewClickListener? = null

    fun setSetCardViewClickListener(listener: SetCardViewClickListener) {
        setCardViewClickListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val cardView: CardView = itemView.findViewById(R.id.cardView)
        val cardView1: SetCardView = itemView.findViewById(R.id.setCardView1)
        val cardView2: SetCardView = itemView.findViewById(R.id.setCardView2)
        val cardView3: SetCardView = itemView.findViewById(R.id.setCardView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sharedViewModel.onTable.size
    }

//    fun updateItemAtPosition(position: Int) {
//        // 更新指定位置的項目
//        notifyItemChanged(position)
//    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cardView1 = holder.cardView1
        val cardView2 = holder.cardView2
        val cardView3 = holder.cardView3

        // 設定點擊事件監聽器
        cardView1.setOnClickListener {
            setCardViewClickListener?.onSetCardViewClick(position, cardView1)
        }
        cardView2.setOnClickListener {
            setCardViewClickListener?.onSetCardViewClick(position, cardView2)
        }
        cardView3.setOnClickListener {
            setCardViewClickListener?.onSetCardViewClick(position, cardView3)
        }

        cardView1.color = sharedViewModel.onTable[position][0].cardColor
        cardView1.shapeCount = sharedViewModel.onTable[position][0].shapeCount
        cardView1.shape = sharedViewModel.onTable[position][0].shape
        cardView1.shading = sharedViewModel.onTable[position][0].shading
        cardView1.cardBackgroundColor  = sharedViewModel.backgroundColor[position][0]

        cardView2.color = sharedViewModel.onTable[position][1].cardColor
        cardView2.shapeCount = sharedViewModel.onTable[position][1].shapeCount
        cardView2.shape = sharedViewModel.onTable[position][1].shape
        cardView2.shading = sharedViewModel.onTable[position][1].shading
        cardView2.cardBackgroundColor  = sharedViewModel.backgroundColor[position][1]

        cardView3.color = sharedViewModel.onTable[position][2].cardColor
        cardView3.shapeCount = sharedViewModel.onTable[position][2].shapeCount
        cardView3.shape = sharedViewModel.onTable[position][2].shape
        cardView3.shading = sharedViewModel.onTable[position][2].shading
        cardView3.cardBackgroundColor  = sharedViewModel.backgroundColor[position][2]
    }
}

