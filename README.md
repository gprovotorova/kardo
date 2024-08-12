# Kardo App

Это бэкенд часть проекта Kardo Awards, позволяющая пользователям обмениваться фото, регистрироваться на мероприятия и конкурсы, смотреть прямые трансляции.

____
Технологический стек:
* Java 17
* Spring Boot
* PostgreSQL
* Lombok
* Maven
* Hibernate
____

Для проекта предусмотрена спецификация API: [ewm-main-service-spec.json]([http://localhost:8080/swagger-ui.html])
____

В прооекте используется база данных Postgres.

![Схема БД](schema.png)
____
# Установка и запуск проекта
1. Клонируйте репозиторий проекта на свою локальную машину
https://github.com/gprovotorova/kardo.git

2. Зайдите в папку проекта в командой строке.

3. Соберите проект
mvn clean package

5. Запустите приложение
java -jar kardo-0.0.1-SNAPSHOT.jar
