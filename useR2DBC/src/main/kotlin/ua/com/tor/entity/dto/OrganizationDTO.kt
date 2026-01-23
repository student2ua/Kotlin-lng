package ua.com.tor.entity.dto

import ua.com.tor.entity.Employee

data class OrganizationDTO(var id: Int?, var name: String) {
    var employees: MutableList<Employee> =  arrayListOf()

    constructor(employees: MutableList<Employee>) : this(null, "") {
        this.employees = employees
    }

    constructor(id: Int, name: String, employees: MutableList<Employee>) : this(id, name) {
        this.employees = employees


    }
}