# ---------- Etapa 1: Build ----------
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia el POM y descarga dependencias primero (mejor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el resto del código y compila
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Etapa 2: Runtime ----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR construido
COPY --from=builder /app/target/*.jar app.jar

ENV SPRING_DATASOURCE_URL="jdbc:mysql://gondola.proxy.rlwy.net:18605/railway"
ENV SPRING_DATASOURCE_USERNAME="root"
ENV SPRING_DATASOURCE_PASSWORD="qnSNnOwdKqdgynTdqELvluHeFBriCoqB"
ENV SPRING_JPA_HIBERNATE_DDL_AUTO="update"
ENV SPRING_JPA_SHOW_SQL="true"
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT="org.hibernate.dialect.MySQLDialect"
ENV SPRING_APPLICATION_NAME="Ayni Platform"
ENV SERVER_PORT=8080
ENV AUTHORIZATION_JWT_SECRET="s8ZV5t0JkQvR2HY4H8LTrJp9nV4gXeS9rN1bOe8q6TtFfR7JxC0mHdL0dF9yPzYp"
ENV AUTHORIZATION_JWT_EXPIRATION_DAYS="14"
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
