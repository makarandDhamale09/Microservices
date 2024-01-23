package com.myproject

import com.hazelcast.core.Hazelcast
import com.hazelcast.map.IMap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmployeeServiceApplication

fun main(args: Array<String>) {
    runApplication<EmployeeServiceApplication>(*args)
    val hz = Hazelcast.newHazelcastInstance()
    val map: IMap<String, String> = hz.getMap("testMap")

    map.set("test", "test")
    map.set("test2", "test2")
}
