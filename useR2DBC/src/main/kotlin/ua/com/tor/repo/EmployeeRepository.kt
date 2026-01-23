package ua.com.tor.repo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import ua.com.tor.entity.Employee

interface EmployeeRepository : ReactiveCrudRepository<Employee, Int> {
    @Query("select id,name,salary, organization_id from employee e where e.organization_id = $1")
    fun findByOrganizationId(organizationId: Int): Flux<Employee>

}