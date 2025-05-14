package au.com.hub24.servicetemplateexample.jpa.service

import au.com.hub24.servicetemplateexample.jpa.model.HasId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository

open class RUDServiceImpl<TObj : HasId, TId : Any, TRepo : JpaRepository<TObj, TId>>(
    val repo: TRepo,
    val clazz: Class<TObj>,
    var useCache: Boolean = false)
    : RUDService<TObj, TId, TRepo> {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)
    var rudMap: MutableMap<TId, TObj>? = if (useCache) HashMap() else null

    override fun count(): Long {
        logger.trace("Counting all ${clazz.simpleName}.")
        val count = if (useCache) rudMap!!.count().toLong() else repo.count()
        logger.trace("Count reports $count found.")
        return count
    }

    override fun delete(objectId: TId): TObj? {
        logger.trace("Deleting ${clazz.simpleName} with id : ${objectId}.")

        if (useCache && rudMap!!.containsKey(objectId)) {
            rudMap!!.remove(objectId)
        }

        val findById = repo.findById(objectId)
        return if (findById.isPresent) {
            repo.deleteById(objectId)
            findById.get()
        } else {
            null
        }
    }

    override fun exists(id: TId): Boolean {
        logger.trace("Checking existence for ${clazz.simpleName} with id : ${id}.")
        val exists = if (useCache) rudMap!!.containsKey(id) else repo.existsById(id)
        logger.trace("$id : ${exists}")
        return exists
    }

    override fun get(objectId: TId): TObj? {
        logger.trace("Getting ${clazz.simpleName} with id : $objectId.")

        if (useCache) {
            return rudMap!![objectId]
        }

        return run {
            val foundObject = repo.findById(objectId).orElse(null)
            logger.trace("Found $foundObject.")
            foundObject
        }
    }

    override fun getAll(): List<TObj> {
        logger.trace("Getting all ${clazz.simpleName}.")

        if (useCache) {
            return rudMap!!.values.toList()
        }

        val findAll = repo.findAll()
        logger.trace("Found ${findAll.size} ${clazz.simpleName}s")
        return findAll
    }

    override fun getAll(idsToGet: Set<TId>): List<TObj> {
        logger.trace("Getting all ${clazz.simpleName}es with ids : ${idsToGet}.")
        if (useCache) {
            val result: MutableList<TObj> = ArrayList()
            for (id in idsToGet) {
                val get = rudMap!!.get(id)
                if (get !== null) {
                    result.add(get)
                }
            }
            return result
        }
        val foundObjects = repo.findAllById(idsToGet)
        logger.trace("Found ${foundObjects.size} ${clazz.simpleName}.")
        return foundObjects
    }

    override fun upsert(toUpsert: TObj): TObj? {
        logger.trace("Upserting ${clazz.simpleName} : ${toUpsert}.")
        if (useCache) {
            val id = toUpsert!!.getId()
            val value = id as TId
            rudMap?.set(value, toUpsert)
        }
        val upsertedObject = repo.save(toUpsert)
        logger.trace("Upserted $upsertedObject.")
        return upsertedObject
    }

    override fun upsert(toUpsert: List<TObj>): List<TObj> {
        logger.trace("Upserting ${clazz.simpleName} : ${toUpsert.size}.")
        val upsertedList = mutableListOf<TObj>()
        if (useCache) {
            for (tObj in toUpsert) {
                val id = tObj.getId()
                val value = id as TId
                rudMap?.set(value, tObj)
            }
        }
        for (tObj in toUpsert) {
            val asUpserted = upsert(tObj)
            upsertedList.add(asUpserted!!)
        }
        logger.trace("Upserted ${upsertedList.size}.")
        return upsertedList
    }

    override fun <TR> getAllSingleParameter(filter: ((TObj) -> Boolean), func: ((TObj) -> TR)): List<TR> {
        logger.trace("Getting all for single parameter ${func}.")
        if (useCache) {
            return rudMap!!.values.filter { a -> filter.invoke(a) }.map { func(it) }
        }

        return repo.findAll().map { func(it) }
    }

    override fun setCacheEnabled() {
        logger.info("Setting cache enabled for ${clazz.simpleName}.")
        this.useCache = true
        val mutableMapOf = mutableMapOf<TId, TObj>()
        this.repo.findAll().associateByTo(mutableMapOf){ it.getId() as TId }
        logger.info("Found ${mutableMapOf.size} cache entries.")
        rudMap = mutableMapOf
    }
}