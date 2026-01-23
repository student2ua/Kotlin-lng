package ua.com.tor.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ua.com.tor.entity.Employee
import ua.com.tor.entity.Organization
import ua.com.tor.entity.dto.OrganizationDTO
import ua.com.tor.repo.OrganizationRepository

@RestController
@RequestMapping("/organizations")
class OrganizationController {
    @Autowired
    lateinit var repository: OrganizationRepository
    @Autowired
    lateinit var clientBuilder: WebClient.Builder

    @GetMapping
    fun findAll(): Flux<Organization> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Mono<Organization> = repository.findById(id)

    @GetMapping("/{id}/withEmployees")
    fun findByIdWithEmployees(@PathVariable id: Int): Mono<OrganizationDTO> {
        val employees: Flux<Employee> = clientBuilder.build().get().uri("http://localhost:8090/employees/organization/$id")
                .retrieve().bodyToFlux(Employee::class.java)
        val org: Mono<Organization> = repository.findById(id)
        return org.zipWith(employees.collectList())
                .map { tuple ->
                    OrganizationDTO(tuple.t1.id as Int, tuple.t1.name, tuple.t2)
                }

    }

    @PostMapping
    fun add(@RequestBody organization: Organization): Mono<Organization> = repository.save(organization)
}