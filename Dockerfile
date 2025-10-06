# ========================
# Etapa 1: Build del JAR
# ========================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar archivos de proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto (sin tests para velocidad)
RUN mvn clean package -DskipTests

# ========================
# Etapa 2: Imagen final
# ========================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el .jar desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# ========================
# Configuraciones
# ========================

# Puerto dinámico para Railway o local (fallback: 8080)
ENV PORT=8080
EXPOSE ${PORT}

# Base de datos Railway (ya puedes cambiar si usas otra)
ENV SPRING_DATASOURCE_URL="jdbc:mysql://gondola.proxy.rlwy.net:18605/railway"
ENV SPRING_DATASOURCE_USERNAME="root"
ENV SPRING_DATASOURCE_PASSWORD="qnSNnOwdKqdgynTdqELvluHeFBriCoqB"
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME="com.mysql.cj.jdbc.Driver"

# Hibernate y JPA
ENV SPRING_JPA_SHOW_SQL="true"
ENV SPRING_JPA_HIBERNATE_DDL_AUTO="update"
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT="org.hibernate.dialect.MySQLDialect"

# JWT Config (256 bits — ✅ fuerte y seguro)
ENV AUTHORIZATION_JWT_SECRET="s8ZV5t0JkQvR2HY4H8LTrJp9nV4gXeS9rN1bOe8q6TtFfR7JxC0mHdL0dF9yPzYp"
ENV AUTHORIZATION_JWT_EXPIRATION_DAYS=7

# Nombre de la aplicación
ENV SPRING_APPLICATION_NAME="Ayni Platform"

# Puerto real de ejecución
ENV SERVER_PORT=${PORT}

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
