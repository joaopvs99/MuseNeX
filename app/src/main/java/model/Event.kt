package model

import java.sql.Timestamp
import java.util.Date


class Event(eventId: String, name: String, date_event_beg: Date, date_event_end: Date, description: String, museumId: String, galeryEvent: Array<String>,categoryId: String) {
    var eventId = eventId
    var name = name
    var date_event_beg = date_event_beg
    var date_event_end = date_event_end
    var description = description
    var museumId = museumId
    var galeryEvent = galeryEvent
    var categoryId = categoryId
    var categoryName = ""
}