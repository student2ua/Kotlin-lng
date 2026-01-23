package ua.com.tor.entity

import org.springframework.data.annotation.Id

data class Organization(var name: String) {
    @Id
    var id: Int? = null
}