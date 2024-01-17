package com.gtappdevelopers.kotlingfgproject

import model.Event
import java.util.Date

data class DiscoveryEventsModal(
    var EventName: String,
    var EventLoc: String,
    var EventDate: Date,
    var EventImg: Array<String>
)
