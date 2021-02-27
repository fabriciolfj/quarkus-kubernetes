package com.github.fabriciolfj.controller

import com.github.fabriciolfj.domain.Funcionario
import com.github.fabriciolfj.repository.FuncionarioRepository
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Timed
import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.math.BigDecimal
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FuncionarioController(val repository: FuncionarioRepository) {

    @POST
    @Transactional
    @Timed(name = "add", unit = MetricUnits.MILLISECONDS)
    fun add(funcionario: Funcionario) : Response {
        repository.persist(funcionario)
        return Response.ok(funcionario).status(201).build()
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Timed(name = "delete", unit = MetricUnits.MILLISECONDS)
    fun delete(@PathParam id: Long) {
        repository.deleteById(id)
    }

    @GET
    @Timed(name = "findAll", unit = MetricUnits.MILLISECONDS)
    fun findAll(): List<Funcionario> = repository.listAll()

    @GET
    @Path("/{id}")
    @Timed(name = "findById", unit = MetricUnits.MILLISECONDS)
    fun findById(@PathParam id: Long): Funcionario? = repository.findById(id)

    @GET
    @Path("/nome/{nome}")
    @Timed(name = "findByNome", unit = MetricUnits.MILLISECONDS)
    fun findByFirstNameAndLastName(@PathParam nome: String): List<Funcionario>
            = repository.findByNome(nome)

    @GET
    @Path("/salario/{salario}")
    @Timed(name = "findBySalario", unit = MetricUnits.MILLISECONDS)
    fun findBySalario(@PathParam salario: BigDecimal): List<Funcionario> = repository.findBySalario(salario)

    @GET
    @Path("/salario-greater-than/{salario}")
    @Timed(name = "findBySalarioGreaterThan", unit = MetricUnits.MILLISECONDS)
    fun findBySalaryGreaterThan(@PathParam salario: BigDecimal): List<Funcionario>
            = repository.findBySalarioGreaterThan(salario)

    @ConfigProperty(name = "property1")
    lateinit var property1: String

    @GET
    @Path("/property1")
    fun property1(): String = property1
}