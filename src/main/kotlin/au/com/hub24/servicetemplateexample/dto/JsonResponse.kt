package au.com.hub24.servicetemplateexample.dto

open class JsonResponse<TObj> (
    open var success: Boolean = false,
    open var result: TObj?,
    open var errorString: String?
) {
    companion object static {
        fun success() : JsonResponse<Void> {
            return JsonResponse(true, null, null)
        }

        fun <TObj> result(obj: TObj): JsonResponse<TObj> {
            return JsonResponse(true, obj, null)
        }

        fun <TObj, T2> result(obj: TObj, func: (TObj) -> T2): JsonResponse<T2> {
            return if (obj == null)
                JsonResponse(false, null, "Object is null")
            else
                JsonResponse(true, func(obj), null)
        }

        fun <TObj> result(obj: TObj, errorOnNull: String): JsonResponse<TObj> {
            return if (obj == null) {
                JsonResponse(false, null, errorOnNull)
            } else {
                JsonResponse(true, obj, null)
            }
        }

        fun <TObj, T2> result(obj: TObj, func: (TObj) -> T2, errorOnNull: String): JsonResponse<T2> {
            return if (obj == null) {
                JsonResponse(false, null, errorOnNull)
            } else {
                val func1 = func(obj)

                return result(func1, errorOnNull)
            }
        }

        fun <TObj> error(errorString: String): JsonResponse<TObj> {
            return JsonResponse(false, null, errorString)
        }
    }
}