package ua.com.tor.repo

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ua.com.tor.entity.Organization

interface OrganizationRepository : ReactiveCrudRepository<Organization, Int> {
}