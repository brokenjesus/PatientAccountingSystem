# Используем официальный образ Java
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем собранный JAR файл в контейнер
COPY target/PatientAccountingSystem-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
