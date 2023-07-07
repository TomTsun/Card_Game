package tw.edu.ncku.iim.rsliu.setcard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tw.edu.ncku.iim.rsliu.setcard.Adapter.CardGridRecyclerViewAdapter
import tw.edu.ncku.iim.rsliu.setcard.databinding.FragmentCardGridBinding


class cardGridFragment : Fragment(), CardGridRecyclerViewAdapter.SetCardViewClickListener {
    private lateinit var sharedViewModel: SharedViewModel

    private lateinit var binding: FragmentCardGridBinding
    private lateinit var adapter: CardGridRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        adapter = CardGridRecyclerViewAdapter(sharedViewModel)
        adapter.setSetCardViewClickListener(this)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        val restartButton = binding.restartButton
        restartButton.setOnClickListener {
            sharedViewModel.restartGame()

            adapter.notifyDataSetChanged()
        }
        val plus_3Button = binding.plus3
        plus_3Button.setOnClickListener {
            sharedViewModel.addOnTable()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onSetCardViewClick(position: Int, cardView: SetCardView) {
//        val context = cardView.context
//        val viewId = cardView.id
//        val count = cardView.shapeCount
//        Toast.makeText(context, "Clicked on view with ID: $viewId, Count: $count", Toast.LENGTH_SHORT).show()
        var cardIndex = when (cardView.id) {
            R.id.setCardView1 -> 0
            R.id.setCardView2 -> 1
            R.id.setCardView3 -> 2
            else -> -1
        }

        val positionIdx = position * sharedViewModel.columns + cardIndex
        sharedViewModel.findValidSelection()

        sharedViewModel.updateSelectedCardIdx(positionIdx) //to examine update and then update

        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
}