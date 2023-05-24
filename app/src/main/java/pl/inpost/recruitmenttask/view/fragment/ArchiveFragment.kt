package pl.inpost.recruitmenttask.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import pl.inpost.recruitmenttask.databinding.FragmentArchiveBinding
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.view.adapter.ArchiveOnClick
import pl.inpost.recruitmenttask.view.adapter.ShipmentItemAdapter
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ArchiveFragment : Fragment() ,ArchiveOnClick,CoroutineScope{
    private val viewModel: ShipmentListViewModel by viewModels()
    private var binding: FragmentArchiveBinding? = null
    private var shipmentItemAdapterPickup: ShipmentItemAdapter? = null
    private var shipmentListArchive = mutableListOf<ShipmentNetwork>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shipmentItemAdapterPickup = ShipmentItemAdapter(this, true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArchiveBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShipmentOfLocal()
        viewModel.shipmentNetworksLocal.observe(viewLifecycleOwner){
            shipmentListArchive.clear()
            it.forEach {shipment->
                if (shipment.operations?.manualArchive == true) {
                    shipmentListArchive.add(shipment)
                }
            }
            binding?.recyclerShipment?.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            binding?.recyclerShipment?.adapter=shipmentItemAdapterPickup

            shipmentItemAdapterPickup?.addData(shipmentListArchive)
        }
    }

    override fun clickListener(shipmentItem: ShipmentNetwork) {
        val archiveShipment = ShipmentNetwork(
            shipmentItem.id,
            shipmentItem.number,
            shipmentItem.expiryDate,
            shipmentItem.storedDate,
            shipmentItem.pickUpDate,
            shipmentItem.openCode,
            shipmentItem.shipmentType,
            shipmentItem.status,
            shipmentItem.eventLog,
            shipmentItem.sender,
            shipmentItem.receiver,
            OperationsNetwork(
                shipmentItem.operations?.delete,
                false,
                shipmentItem.operations?.collect,
                shipmentItem.operations?.highlight,
                shipmentItem.operations?.expandAvizo,
                shipmentItem.operations?.endOfWeekCollection
            )
        )
        viewModel.updateShipment(archiveShipment)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


}