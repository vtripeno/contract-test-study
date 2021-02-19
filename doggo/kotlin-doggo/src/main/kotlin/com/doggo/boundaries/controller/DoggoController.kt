package com.doggo.boundaries.controller

import com.doggo.boundaries.controller.request.DoggoRequest
import com.doggo.boundaries.controller.response.DataModelResponse
import com.doggo.boundaries.controller.response.Doggo
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

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

  @GetMapping("/call-bff")
  fun callBff() {
    val webClient = WebClient.create("http://localhost:8000")

    val body = object {
      var query: String = "{ helloDoggo { data { welcome doggo}}}"
    }

    webClient.post()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .bodyToMono(String::class.java)
        .block()
  }

  @PostMapping
  fun create(@RequestBody request: DoggoRequest): ResponseEntity<DataModelResponse<Doggo>> =
    ResponseEntity.ok(
        DataModelResponse(
            Doggo(
                name = request.name,
                race = request.race
            )
        )
    )

}
