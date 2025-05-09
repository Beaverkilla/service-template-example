package au.com.hub24.servicetemplateexample.controller

import au.com.hub24.servicetemplateexample.jpa.model.Organisation
import au.com.hub24.servicetemplateexample.jpa.repo.OrgRepo
import au.com.hub24.servicetemplateexample.jpa.service.OrgService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/api/org"])
class OrgCtlr(private val serv: OrgService) :
    RUDControllerImpl<Organisation, UUID, OrgRepo, OrgService>(Organisation::class.java, serv)