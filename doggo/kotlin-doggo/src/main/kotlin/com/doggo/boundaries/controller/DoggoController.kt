package com.doggo.boundaries.controller

import com.doggo.boundaries.controller.response.DataModelResponse
import com.doggo.boundaries.controller.response.Doggo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/doggos")
class DoggoController {

    @GetMapping
    fun listAllDoggos(): ResponseEntity<DataModelResponse<List<Doggo>>> {
        return ResponseEntity.ok(DataModelResponse(doggoBuild()))
    }

    private fun doggoBuild(): List<Doggo> {
        return listOf(
                Doggo(name = "Rex", race = "no race"),
                Doggo(name = "Snoopy", race = "beagle")
        )
    }
}
