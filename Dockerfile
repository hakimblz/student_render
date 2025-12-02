# # Étape 1 : Choisir l'image Java
# FROM eclipse-temurin:17-jdk-jammy

# # Étape 2 : Définir le dossier de travail
# WORKDIR /app

# # Étape 3 : Copier Maven Wrapper et pom.xml pour downloader les dépendances
# COPY mvnw .
# COPY pom.xml .
# COPY .mvn .mvn

# # Autoriser l’exécution du wrapper Maven
# RUN chmod +x mvnw

# # Télécharger les dépendances Maven sans build complet
# RUN ./mvnw dependency:go-offline

# # Étape 4 : Copier le code source
# COPY src ./src

# # Étape 5 : Builder l’application sans les tests
# RUN ./mvnw clean package -DskipTests

# # Étape 6 : Exposer le port de l'application
# EXPOSE 8080

# # Étape 7 : Lancer le JAR
# CMD ["java", "-jar", "target/student_service-0.0.1-SNAPSHOT.jar"]
# Image Java 17
FROM eclipse-temurin:17-jdk


# Copie le JAR
COPY target/student-service-0.0.1-SNAPSHOT.jar app.jar

# Expose le port utilisé par Render
EXPOSE 10000

# Lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
