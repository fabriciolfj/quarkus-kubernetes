# Quarkus kubernetes
- Quarkus geralmente é definido como uma estrutura java kubernetes nativa. Permite gerar automaticamente  recursos kubernetes, com base nas predefinições e na configuração fornecida pelo utilizador. (extensão quarkus dekorate)
- Fornece também uma extensão para construir e enviar imagens para um registry.
- Permite aos programadores a utlizar configmaps como fonte de configuração.
- Podemos usar fabric8 kubernetes client, para interagir com o cluster, por exemplo, durante tests unitários.

### Construindo a aplicação
- A fim de construir uma imagem Docker com a aplicação, precisamos de activar a opção quarkus.container-image.build durante a fase de construção Maven. 
- Caso também queira implantar e executar um conteiner com a aplicação na sua instância local Kubernetes, precisa de activar a opção quarkus.kubernetes.deploy.
- Caso seu kubernetes esteja na nuvem, precisa enviar a imagem para um registro remoto antes de ser implantado. Para isso ative a opção quarkus.container-image.push
```
 clean package -Dquarkus.container-image.build=true -Dquarkus.kubernetes.deploy=true -Dquarkus.container-image.push=true
```

### Resultado
- Os manifestos ficam na pasta target/kubernetes
- diciona algumas anotações como porta ou caminho ao ponto final da métrica utilizada pela Prometheus para monitorizar a aplicação.
- adiciona Git commit id, URL de repositório, e a nossa anotação personalizada definida em application.properties.
- adiciona etiquetas com o nome da aplicação, versão (retirado de Maven pom.xml), e o nosso tipo de aplicação de etiquetas personalizadas.
- injecta o nome do namespace Kubernetes no container.
- Injecta a referência ao postgres-secret, definido em application.properties.
- Injecta a referência ao postgres-config, definido em application.properties.
- O nome da imagem é automaticamente criado. É baseado no artefacto Maven e na versão.
- A definição de vivacidade e prontidão é gerada se o módulo Maven quarkus-smallrye-health estiver presente.
