package org.example.cleanarchitecturelecture.helper

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

object ConcurrentTestHelper {
    fun <T, D> executeAsyncTasksWithData(
        taskCount: Int,
        data: List<D>,
        task: (D) -> T,
    ): List<Boolean> {
        val executorService = Executors.newFixedThreadPool(200)
        try {
            val futureList =
                (0 until taskCount).map { i ->
                    CompletableFuture.supplyAsync({
                        try {
                            task(data[i])
                            true
                        } catch (e: Exception) {
                            e.printStackTrace()
                            false
                        }
                    }, executorService)
                }
            CompletableFuture.allOf(*futureList.toTypedArray()).join()
            return futureList.map { it.get() }
        } finally {
            executorService.shutdown()
        }
    }
}
