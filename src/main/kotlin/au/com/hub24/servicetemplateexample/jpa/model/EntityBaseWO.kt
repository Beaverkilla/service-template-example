package au.com.hub24.servicetemplateexample.jpa.model

import jakarta.persistence.MappedSuperclass
import java.sql.Timestamp
import java.time.Instant

@MappedSuperclass
abstract class EntityBaseWO(
    var creationTimestamp: Timestamp,
    var lastUpdatedTimestamp: Timestamp,
    var deletedTimestamp: Timestamp?
                           ) {
    constructor() : this(Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), null)
}