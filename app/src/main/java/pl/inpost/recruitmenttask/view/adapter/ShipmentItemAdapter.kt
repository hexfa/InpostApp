package pl.inpost.recruitmenttask.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.databinding.ShipmentListItemBinding
import pl.inpost.recruitmenttask.model.local.ShipmentNetwork
import pl.inpost.recruitmenttask.model.local.ShipmentType

internal class ShipmentItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mContext: Context
    private val shipmentItemList: MutableList<ShipmentNetwork> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val holderShipmentItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.shipment_list_item,
            parent,
            false
        )
        return ShipmentItemHolder(holderShipmentItemBinding)
    }

    override fun getItemCount(): Int {

        return shipmentItemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<ShipmentNetwork>) {
        this.shipmentItemList.clear()
        this.shipmentItemList.addAll(list)
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): ShipmentNetwork {
        return shipmentItemList[position]
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShipmentItemHolder).onBind(getItem(position))
    }

    inner class ShipmentItemHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(shipmentItem: ShipmentNetwork) {
            val holderShipmentItemBinding = dataBinding as ShipmentListItemBinding
            holderShipmentItemBinding.shipmentNumberValue.text = shipmentItem.number
            var status = ""
            when (shipmentItem.status) {
                "READY_TO_PICKUP" -> status = "Ready to Pickup"
                "CONFIRMED" -> status = "Confirmed"
                "OUT_FOR_DELIVERY" -> status = "Out for Delivery"
                "AVIZO" -> status = "Avizo"
                "SENT_FROM_SOURCE_BRANCH" -> status = "Sent from source branch"
                "NOT_READY" -> status = "not ready"
                else -> status = "Other"
            }
            holderShipmentItemBinding.shipmentStatusValue.text = status
            holderShipmentItemBinding.shipmentSenderValue.text = shipmentItem.sender?.email
            if (shipmentItem.shipmentType == ShipmentType.PARCEL_LOCKER.name) {
                holderShipmentItemBinding.shipmentType.setImageResource(R.drawable.parcel_locker)
            }

            if (shipmentItem.operations?.highlight == true) {
                holderShipmentItemBinding.starIcon.setImageResource(R.drawable.star_icon_yellow)
            }else{
                holderShipmentItemBinding.starIcon.setImageResource(R.drawable.star_icon)

            }


        }

    }
}