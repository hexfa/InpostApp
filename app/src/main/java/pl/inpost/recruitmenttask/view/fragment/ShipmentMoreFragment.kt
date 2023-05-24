package pl.inpost.recruitmenttask.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.databinding.FragmentShipmentListBinding
import pl.inpost.recruitmenttask.databinding.FragmentShipmentMoreBinding
import pl.inpost.recruitmenttask.viewmodel.ShipmentListViewModel


class ShipmentMoreFragment : Fragment() {

    private lateinit var viewModel:ShipmentListViewModel
    private var binding: FragmentShipmentMoreBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShipmentMoreBinding.inflate(inflater, container, false)
        return requireNotNull(binding).root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShipmentListViewModel::class.java]

        viewModel.shipmentLiveData.observe(viewLifecycleOwner, Observer {shipment->
            binding?.number?.text = "Number: ${shipment.number}"
            binding?.expiryDate?.text = "Expiry Date: ${shipment.expiryDate}"
            binding?.storedDate?.text = "Stored Date: ${shipment.storedDate}"
            binding?.openCode?.text = "Open Code: ${shipment.openCode}"
            binding?.shipmentType?.text = "Shipment Type: ${shipment.shipmentType}"
            binding?.status?.text= "Status: ${shipment.status}"
            binding?.sender?.text = "Sender Email: ${shipment.sender?.email}"
            binding?.sender?.text= "Receiver Email: ${shipment.receiver?.email}"

        })

    }

}