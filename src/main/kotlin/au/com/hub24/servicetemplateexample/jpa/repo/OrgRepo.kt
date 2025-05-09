package au.com.hub24.servicetemplateexample.jpa.repo

import au.com.hub24.servicetemplateexample.jpa.model.Organisation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrgRepo : JpaRepository<Organisation, UUID>