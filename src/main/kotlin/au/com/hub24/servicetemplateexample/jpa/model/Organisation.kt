package au.com.hub24.servicetemplateexample.jpa.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "organisation")
class Organisation(
    @Id
    var orgId: UUID,
    var orgName: String,
    var orgContactId: UUID? = null,
) : EntityBaseWO(), HasId {
    override fun getId(): UUID {
        return this.orgId
    }
}