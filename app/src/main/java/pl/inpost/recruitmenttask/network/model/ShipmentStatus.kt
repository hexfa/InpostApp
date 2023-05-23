package pl.inpost.recruitmenttask.network.model

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.R

/**
 * Order of statuses
 * 1. CREATED
 * 2. CONFIRMED
 * 3. ADOPTED_AT_SOURCE_BRANCH
 * 4. SENT_FROM_SOURCE_BRANCH
 * 5. ADOPTED_AT_SORTING_CENTER
 * 6. SENT_FROM_SORTING_CENTER
 * 7. OTHER
 * 8. DELIVERED
 * 9. RETURNED_TO_SENDER
 * 10. AVIZO
 * 11. OUT_FOR_DELIVERY
 * 12. READY_TO_PICKUP
 * 13. PICKUP_TIME_EXPIRED
 */
enum class ShipmentStatus(
    @StringRes val nameRes: Int
) {
    ADOPTED_AT_SORTING_CENTER(R.string.status_adopted_at_sorting_center),
    SENT_FROM_SORTING_CENTER(R.string.status_sent_from_sorting_center),
    ADOPTED_AT_SOURCE_BRANCH(R.string.status_adopted_at_source_branch),
    SENT_FROM_SOURCE_BRANCH(R.string.status_sent_from_source_branch),
    AVIZO(R.string.status_avizo),
    CONFIRMED(R.string.status_confirmed),
    CREATED(R.string.status_created),
    DELIVERED(R.string.status_delivered),
    OTHER(R.string.status_other),
    OUT_FOR_DELIVERY(R.string.status_out_for_delivery),
    PICKUP_TIME_EXPIRED(R.string.status_pickup_time_expired),
    READY_TO_PICKUP(R.string.status_ready_to_pickup),
    RETURNED_TO_SENDER(R.string.status_returned_to_sender);
}
