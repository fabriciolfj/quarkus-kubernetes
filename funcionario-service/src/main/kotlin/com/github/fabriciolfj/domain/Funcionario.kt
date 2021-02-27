package com.github.fabriciolfj.domain

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Funcionario(
    var nome: String = "",
    var salario: BigDecimal = BigDecimal.ZERO,
    var departamentoId: Int? = null) : PanacheEntity()