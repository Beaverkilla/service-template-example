package au.com.hub24.servicetemplateexample.controller

import au.com.hub24.servicetemplateexample.dto.JsonResponse
import au.com.hub24.servicetemplateexample.jpa.model.HasId
import au.com.hub24.servicetemplateexample.jpa.service.RUDService
import org.springframework.data.jpa.repository.JpaRepository

interface RUDController<TObj : HasId, TId, TRepo : JpaRepository<TObj, TId>, TServ : RUDService<TObj, TId, TRepo>> {
    fun count() : JsonResponse<Long>
    fun delete(toDeleteId: TId) : JsonResponse<TObj?>
    fun get(objId: TId) : JsonResponse<TObj?>
    fun getAll() : JsonResponse<List<TObj>>
    fun upsert(toUpsert: TObj) : JsonResponse<TObj?>
}