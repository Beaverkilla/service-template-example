package au.com.hub24.servicetemplateexample.jpa.model

import java.util.UUID

interface HasId {
    fun getId() : UUID
}