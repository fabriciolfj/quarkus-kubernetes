package com.github.fabriciolfj.repository

import com.github.fabriciolfj.domain.Funcionario
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import java.math.BigDecimal
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FuncionarioRepository: PanacheRepository<Funcionario> {

    fun findByNome(nome: String) : List<Funcionario> = list("nome = ?1", nome)
    fun findBySalario(salario: BigDecimal) : List<Funcionario> = list("salario", salario)
    fun findBySalarioGreaterThan(salario: BigDecimal) : List<Funcionario> = list("salario > ?1", salario)
}