package au.com.hub24.servicetemplateexample.controller

import au.com.hub24.servicetemplateexample.dto.JsonResponse
import au.com.hub24.servicetemplateexample.jpa.model.HasId
import au.com.hub24.servicetemplateexample.jpa.service.RUDService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

open class RUDControllerImpl<TObj : HasId, TId, TRepo : JpaRepository<TObj, TId>, TServ : RUDService<TObj, TId, TRepo>>(
    private val clazz: Class<TObj>,
    private val serv: TServ
) : RUDController<TObj, TId, TRepo, TServ> {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/{objId}"], produces = [APPLICATION_JSON_VALUE])
    override fun get(@PathVariable objId: TId): JsonResponse<TObj?> {
        logger.trace("Getting ${clazz.simpleName} with id : ${objId}.")

        return if (objId == null) JsonResponse.error("Object id is null.")
        else JsonResponse.result(serv.get(objId),
                                 "Failed to retrieve object.")
    }

    @GetMapping(value = ["/count"], produces = [APPLICATION_JSON_VALUE])
    override fun count(): JsonResponse<Long> {
        logger.trace("Counting ${clazz.simpleName}")

        return JsonResponse.result(serv.count())
    }

    @GetMapping(value = ["/all"], produces = [APPLICATION_JSON_VALUE])
    override fun getAll(): JsonResponse<List<TObj>> {
        logger.trace("Getting all ${clazz.simpleName}s.")

        return JsonResponse.result(serv.getAll(), "Failed to get all as a list.")
    }

    @PostMapping(value = [""], consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE])
    override fun upsert(@RequestBody toUpsert: TObj): JsonResponse<TObj?> {
        logger.trace("Upserting ${clazz.simpleName} : ${toUpsert}.")

        return JsonResponse.result(serv.upsert(toUpsert), "Failed to upsert object.")
    }

    @DeleteMapping(value = ["/{toDeleteId}"], produces = [APPLICATION_JSON_VALUE])
    override fun delete(@PathVariable toDeleteId: TId): JsonResponse<TObj?> {
        logger.trace("Deleting ${clazz.simpleName} with id : ${toDeleteId}.")

        return if (toDeleteId == null) {
            JsonResponse.error("Object id is null.")
        } else {
            JsonResponse.result(serv.delete(toDeleteId), "Failed to delete object.");
        }
    }
}