package pl.inpost.recruitmenttask.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.inpost.recruitmenttask.databinding.FragmentShipmentListBinding
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.view.adapter.ShipmentItemAdapter
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel

@AndroidEntryPoint
class ShipmentListFragment : Fragment() {

    private val viewModel: ShipmentListViewModel by viewModels()
    private var binding: FragmentShipmentListBinding? = null
    private var shipmentItemAdapterInTransit: ShipmentItemAdapter? = null
    private var shipmentItemAdapterPickup: ShipmentItemAdapter? = null
    private var shipmentItemAdapterInProcess: ShipmentItemAdapter? = null
    private var shipmentItemAdapterPending: ShipmentItemAdapter? = null

    private var shipmentItemsInTransit = mutableListOf<ShipmentNetwork>()
    private var shipmentItemsPickup = mutableListOf<ShipmentNetwork>()
    private var shipmentItemInProgress = mutableListOf<ShipmentNetwork>()
    private var shipmentItemPending = mutableListOf<ShipmentNetwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shipmentItemAdapterInTransit = ShipmentItemAdapter()
        shipmentItemAdapterPickup = ShipmentItemAdapter()
        shipmentItemAdapterInProcess = ShipmentItemAdapter()
        shipmentItemAdapterPending = ShipmentItemAdapter()

        //setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShipmentListBinding.inflate(inflater, container, false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ShipmentListFragment", "onViewCreated: " + "shipments")

        viewModel.getShipment().observe(requireActivity()) { shipments ->
            //ToDo list should be fill here *****************************************
            // *****************************************************************************************************
            shipments.shipments.forEach {
                when(it.status){
                    "OUT_FOR_DELIVERY"->shipmentItemsInTransit.add(it)
                    "RETURNED_TO_SENDER"->shipmentItemsInTransit.add(it)
                    "SENT_FROM_SOURCE_BRANCH"->shipmentItemsInTransit.add(it)
                    "READY_TO_PICKUP"->shipmentItemsPickup.add(it)
                    "DELIVERED"->shipmentItemsPickup.add(it)
                    "CONFIRMED"->shipmentItemInProgress.add(it)
                    "ADOPTED_AT_SOURCE_BRANCH"->shipmentItemInProgress.add(it)
                    "CREATED"->shipmentItemInProgress.add(it)
                    "SENT_FROM_SOURCE_BRANCH"->shipmentItemInProgress.add(it)
                    "AVIZO"->shipmentItemPending.add(it)
                    "NOT_READY"->shipmentItemPending.add(it)

                    else->shipmentItemPending.add(it)

                }
            }


            setUpAdapter(shipmentItemsInTransit,shipmentItemsPickup,shipmentItemInProgress,shipmentItemPending)
        }
    }

    private fun setUpAdapter(
        shipmentItemsInTransit: List<ShipmentNetwork>?,
        shipmentItemsPickup: List<ShipmentNetwork>?,
        shipmentItemInProgress: List<ShipmentNetwork>?,
        shipmentItemPending: List<ShipmentNetwork>?,
    ) {
        if (shipmentItemsInTransit != null) {
            //In transit
            shipmentItemAdapterInTransit?.addData(shipmentItemsInTransit)
            binding?.recyclerShipmentInTransit?.adapter = shipmentItemAdapterInTransit
            binding?.recyclerShipmentInTransit?.isNestedScrollingEnabled = false

            binding?.recyclerShipmentInTransit?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        //Pickup
        if (shipmentItemsPickup != null) {

            shipmentItemAdapterPickup?.addData(shipmentItemsPickup)
            binding?.recyclerShipmentPickup?.adapter = shipmentItemAdapterPickup
            binding?.recyclerShipmentPickup?.isNestedScrollingEnabled = false

            binding?.recyclerShipmentPickup?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        //In progress
        if (shipmentItemInProgress != null) {

            shipmentItemAdapterInProcess?.addData(shipmentItemInProgress)
            binding?.recyclerShipmentInProgress?.adapter = shipmentItemAdapterInProcess
            binding?.recyclerShipmentInProgress?.isNestedScrollingEnabled = false

            binding?.recyclerShipmentInProgress?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        //Pending
        if (shipmentItemPending != null) {

            shipmentItemAdapterPending?.addData(shipmentItemPending)
            binding?.recyclerShipmentPending?.adapter = shipmentItemAdapterPending
            binding?.recyclerShipmentPending?.isNestedScrollingEnabled = false

            binding?.recyclerShipmentPending?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
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
