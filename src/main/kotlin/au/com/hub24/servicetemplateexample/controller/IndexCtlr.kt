package au.com.hub24.servicetemplateexample.controller

import au.com.hub24.servicetemplateexample.jpa.model.Organisation
import au.com.hub24.servicetemplateexample.jpa.repo.OrgRepo
import au.com.hub24.servicetemplateexample.jpa.service.OrgService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/")
class IndexCtlr {

    @GetMapping
    fun getIndex(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatusCode.valueOf(301)).header("Location", "/swagger-ui/index.html").body("")
    }
}