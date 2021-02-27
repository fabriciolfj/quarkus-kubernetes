package com.github.fabriciolfj

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application


@OpenAPIDefinition(info = Info(title = "Funcionario api", version = "1.0"))
class FuncionarioApplication : Application()