package ua.com.tor.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ua.com.tor.entity.Employee
import ua.com.tor.repo.EmployeeRepository

@RestController
@RequestMapping("/employees")
class EmployeeController {
    @Autowired
    lateinit var repository: EmployeeRepository

    @GetMapping
    fun findAll(): Flux<Employee> = repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Mono<Employee> = repository.findById(id)

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int): Flux<Employee> = repository.findByOrganizationId(organizationId)

    @PostMapping
    fun add(@RequestBody employee: Employee): Mono<Employee> = repository.save(employee)

}