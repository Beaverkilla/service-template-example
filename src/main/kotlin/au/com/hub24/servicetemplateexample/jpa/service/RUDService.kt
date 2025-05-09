package au.com.hub24.servicetemplateexample.jpa.service

import au.com.hub24.servicetemplateexample.jpa.model.HasId
import org.springframework.data.jpa.repository.JpaRepository

interface RUDService<TObj : HasId, TId, TRepo : JpaRepository<TObj, TId>> {
    fun count() : Long
    fun delete(objectId: TId) : TObj?
    fun exists(id: TId) : Boolean
    fun get(objectId: TId) : TObj?
    fun getAll() : List<TObj>
    fun getAll(idsToGet: Set<TId>) : List<TObj>
    fun upsert(toUpsert : TObj) : TObj?
    fun upsert(toUpsert : List<TObj>) : List<TObj>
    fun <TR> getAllSingleParameter(filter: ((TObj) -> Boolean), func: ((TObj) -> TR)): List<TR>
    fun setCacheEnabled()
}