package pl.inpost.recruitmenttask.view.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.databinding.FragmentShipmentListBinding
import pl.inpost.recruitmenttask.model.local.OperationsNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.view.adapter.ArchiveOnClick
import pl.inpost.recruitmenttask.view.adapter.ShipmentItemAdapter
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel
import kotlin.coroutines.CoroutineContext

private const val TAG = "ShipmentListFragment"

@AndroidEntryPoint
class ShipmentListFragment() : Fragment(), ArchiveOnClick, CoroutineScope {

    private lateinit var viewModel: ShipmentListViewModel
    private var binding: FragmentShipmentListBinding? = null
    private var shipmentItemAdapterInTransit: ShipmentItemAdapter? = null
    private var shipmentItemAdapterPickup: ShipmentItemAdapter? = null
    private var shipmentItemAdapterInProcess: ShipmentItemAdapter? = null
    private var shipmentItemAdapterPending: ShipmentItemAdapter? = null
    private var shipmentFilterItemAdapter: ShipmentItemAdapter? = null
    private var shipmentItemsInTransit = mutableListOf<ShipmentNetwork>()
    private var shipmentItemsPickup = mutableListOf<ShipmentNetwork>()
    private var shipmentItemInProgress = mutableListOf<ShipmentNetwork>()
    private var shipmentItemPending = mutableListOf<ShipmentNetwork>()
    private var shipmentListFilter = mutableListOf<ShipmentNetwork>()
    private var shipmentListArchive = mutableListOf<ShipmentNetwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shipmentItemAdapterInTransit = ShipmentItemAdapter(this, false)
        shipmentItemAdapterPickup = ShipmentItemAdapter(this, false)
        shipmentItemAdapterInProcess = ShipmentItemAdapter(this, false)
        shipmentItemAdapterPending = ShipmentItemAdapter(this, false)
        shipmentFilterItemAdapter = ShipmentItemAdapter(this, false)

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
        viewModel = ViewModelProvider(requireActivity())[ShipmentListViewModel::class.java]

        // Set the click listener for the menu button
        binding?.menuButton?.setOnClickListener {
            Log.d(TAG, "onViewCreated: " + "clicked")
            showPopupMenu(it)
        }
        binding?.archiveButton?.setOnClickListener {

            findNavController().navigate(R.id.action_shipmentListFragment_to_archiveFragment)

        }
        binding?.swipeRefreshLayout?.setOnRefreshListener {
            // Perform your data refresh operation here
            viewModel.getShipments()
            viewModel.getShipmentOfLocal()
            // When the refresh is complete, call setRefreshing(false) to stop the loading indicator
            binding?.swipeRefreshLayout?.isRefreshing = false
        }
        viewModel.getShipments()
        viewModel.getShipmentOfLocal()
        getShipment()

    }

    private fun getShipment() {

        viewModel.shipmentNetworksRemoteLiveData.observe(requireActivity()) { shipments ->
            viewModel.shipmentNetworksLocalLiveData.observe(viewLifecycleOwner) { list ->
                shipmentListArchive.clear()
                shipmentItemPending.clear()
                shipmentItemInProgress.clear()
                shipmentItemsInTransit.clear()
                shipmentItemsPickup.clear()
                shipmentListFilter.clear()

                if (list.isEmpty()) {
                    if (shipments != null) {
                        viewModel.addItems(shipments)
                        runBlocking {
                            viewModel.getShipmentOfLocal()
                        }
                    }
                } else {
                    list.let {
                        shipmentListFilter.addAll(it)
                    }
                    list.forEach {
                        if (it.operations?.manualArchive == false) {
                            shipmentListArchive.add(it)
                        }
                    }

                    shipmentListArchive.forEach {
                        when (it.status) {
                            "OUT_FOR_DELIVERY" -> shipmentItemsInTransit.add(it)
                            "RETURNED_TO_SENDER" -> shipmentItemsInTransit.add(it)
                            "SENT_FROM_SOURCE_BRANCH" -> shipmentItemsInTransit.add(it)
                            "READY_TO_PICKUP" -> shipmentItemsPickup.add(it)
                            "DELIVERED" -> shipmentItemsPickup.add(it)
                            "CONFIRMED" -> shipmentItemInProgress.add(it)
                            "ADOPTED_AT_SOURCE_BRANCH" -> shipmentItemInProgress.add(it)
                            "CREATED" -> shipmentItemInProgress.add(it)
                            "SENT_FROM_SOURCE_BRANCH" -> shipmentItemInProgress.add(it)
                            "AVIZO" -> shipmentItemPending.add(it)
                            "NOT_READY" -> shipmentItemPending.add(it)

                            else -> shipmentItemPending.add(it)

                        }
                    }
                    setUpAdapter(
                        shipmentItemsInTransit,
                        shipmentItemsPickup,
                        shipmentItemInProgress,
                        shipmentItemPending
                    )
                }
            }


        }
    }

    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(requireContext(), anchorView)
        popupMenu.menuInflater.inflate(R.menu.shipment_list_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_expire_date -> {
                    showExpiryDateItem(shipmentListArchive)
                    true
                    // Handle "Expire Date" menu item click
                }

                R.id.menu_pickup_date -> {
                    // Handle "Pickup Date" menu item click
                    showPickUpDateItem(shipmentListArchive)

                    true

                }

                R.id.menu_sorted_item -> {
                    // Handle "Sorted Item" menu item click
                    showStoredDateItem(shipmentListArchive)
                    true


                }

                R.id.menu_number -> {
                    // Handle "Number" menu item click
                    showNumberItem(shipmentListArchive)
                    true


                }

                R.id.menu_default -> {
                    // Handle "Number" menu item click
                    hideFilterItem()

                    true
                }

                R.id.menu_status_order -> {
                    // Handle "status_order" menu item click
                    showStatusOrderItem(shipmentListArchive)

                    true
                }
                // Add more menu item cases as needed

                else -> false
            }
        }

        popupMenu.show()
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

    private fun showExpiryDateItem(shipments: List<ShipmentNetwork>) {
        val list = mutableListOf<ShipmentNetwork>()
        for (shipment in shipments) {
            if (shipment.expiryDate?.equals("") == false) {
                //Log.d(TAG, "showExpiryDateItem: $shipment")
                list.add(shipment)
            }
        }
        showFilterItem(list)

    }

    private fun showPickUpDateItem(shipments: List<ShipmentNetwork>) {
        val list = mutableListOf<ShipmentNetwork>()
        for (shipment in shipments) {
            if (shipment.pickUpDate?.equals("") == false) {
                list.add(shipment)
            }
        }
        showFilterItem(list)

    }

    private fun showStoredDateItem(shipments: List<ShipmentNetwork>) {
        val list = mutableListOf<ShipmentNetwork>()
        for (shipment in shipments) {
            if (shipment.storedDate?.equals("") == false) {
                list.add(shipment)
            }
        }
        showFilterItem(list)

    }

    private fun showNumberItem(shipments: List<ShipmentNetwork>) {
        val list = mutableListOf<ShipmentNetwork>()
        for (shipment in shipments) {
            if (shipment.number != "") {
                list.add(shipment)
            }
        }
        showFilterItem(list)

    }

    private fun showStatusOrderItem(shipments: List<ShipmentNetwork>) {
        //Log.d(TAG, "showStatusOrderItem: $shipments")
        val sortedShipments = shipments.sortedBy { it.getShipmentStatus().ordinal }
        showFilterItem(sortedShipments)
        //Log.d(TAG, "showStatusOrderItem: $sortedShipments")
    }


    private fun showFilterItem(shipment: List<ShipmentNetwork>?) {
        binding?.layoutInProgress?.visibility = View.GONE
        binding?.layoutPending?.visibility = View.GONE
        binding?.layoutPickup?.visibility = View.GONE
        binding?.layoutInTransit?.visibility = View.GONE
        binding?.layoutFilter?.visibility = View.VISIBLE
        //Log.d(TAG, "showFilterItem: $shipment")
        if (shipment != null) {
            shipmentFilterItemAdapter?.addData(shipment)
        }
        Log.d(TAG, "showFilterItem: $shipmentFilterItemAdapter")
        binding?.recyclerFilter?.adapter = shipmentFilterItemAdapter
        binding?.recyclerFilter?.isNestedScrollingEnabled = false

        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun hideFilterItem() {
        binding?.layoutInProgress?.visibility = View.VISIBLE
        binding?.layoutPending?.visibility = View.VISIBLE
        binding?.layoutPickup?.visibility = View.VISIBLE
        binding?.layoutInTransit?.visibility = View.VISIBLE
        binding?.layoutFilter?.visibility = View.GONE
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun sortItem(shipment: List<ShipmentNetwork>) {
        val shipments: List<ShipmentNetwork> = shipment

// Sort the shipments based on the expiryDate field in ascending order
        val sortedShipments = shipments.sortedBy { shipmentItem ->
            shipmentItem.expiryDate?.toEpochSecond() ?: 0L
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
                true,
                shipmentItem.operations?.collect,
                shipmentItem.operations?.highlight,
                shipmentItem.operations?.expandAvizo,
                shipmentItem.operations?.endOfWeekCollection
            )
        )
        viewModel.updateShipment(archiveShipment)
    }

    override fun goToMoreFragment(shipmentItem: ShipmentNetwork) {
        viewModel.shipmenDetailLiveData.value = shipmentItem
        findNavController().navigate(R.id.action_shipmentListFragment_to_shipmentMoreFragment)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

}
