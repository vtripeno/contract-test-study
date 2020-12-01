package com.doggo.boundaries.controller

import com.doggo.boundaries.controller.request.Doggo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/doggos")
class DoggoController {

    @GetMapping
    fun listAllDoggos(): ResponseEntity<List<Doggo>> {

        return ResponseEntity.ok(listOf())
    }
}