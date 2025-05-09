package au.com.hub24.servicetemplateexample.jpa.service

import au.com.hub24.servicetemplateexample.jpa.model.Organisation
import au.com.hub24.servicetemplateexample.jpa.repo.OrgRepo
import java.util.*

interface OrgService : RUDService<Organisation, UUID, OrgRepo>