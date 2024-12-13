# Используем официальный образ Tomcat
FROM tomcat:10.1-jdk17

# Устанавливаем рабочую директорию
WORKDIR /usr/local/tomcat/webapps/

# Копируем ваш WAR-файл в папку webapps Tomcat
COPY target/MovieReviewPlatform-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war


# Открываем порт 8080
EXPOSE 8080

ENTRYPOINT ["catalina.sh", "run"]
# Стартовая команда уже задана в базовом образе Tomcat
