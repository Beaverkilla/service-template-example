package au.com.hub24.servicetemplateexample.jpa.service

import au.com.hub24.servicetemplateexample.jpa.model.Organisation
import au.com.hub24.servicetemplateexample.jpa.repo.OrgRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrgServiceImpl(private val orgRepo: OrgRepo)
    : RUDServiceImpl<Organisation, UUID, OrgRepo>(orgRepo, Organisation::class.java), OrgService {
        init {
            setCacheEnabled()
        }
    }