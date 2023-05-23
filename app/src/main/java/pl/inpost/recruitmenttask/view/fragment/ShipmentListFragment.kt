package pl.inpost.recruitmenttask.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.databinding.FragmentShipmentListBinding
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel

@AndroidEntryPoint
class ShipmentListFragment : Fragment() {

    private val viewModel: ShipmentListViewModel by viewModels()
    private var binding: FragmentShipmentListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shipment_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentShipmentListBinding.inflate(inflater, container, false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(requireActivity()) { shipments ->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = ShipmentListFragment()
    }
}
