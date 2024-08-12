# Kardo App

Это бэкенд часть проекта [Kardo Awards](https://kardoaward.com), позволяющая пользователям обмениваться фото, регистрироваться на мероприятия и конкурсы, смотреть прямые трансляции.
____
Команда backend разработки:

[Галина Карлова](https://github.com/gprovotorova)

[Ольга Шаталова](https://github.com/ol5ga) 
____
Технологический стек:
* Java 17
* Spring Boot 2
* PostgreSQL
* Lombok
* Maven
* Hibernate
____

Для проекта предусмотрена спецификация API:
[kardo-api.json](kardo-api.json)

Документация также доступна по ссылке:
[http://51.250.32.67:8080/swagger-ui/index.html](http://51.250.32.67:8080/swagger-ui/index.html)
____

В прооекте используется база данных Postgres.

![Схема БД](schema.png)
____
# Установка и запуск проекта
1. Клонируйте репозиторий проекта на свою локальную машину
https://github.com/gprovotorova/kardo.git

2. Зайдите в папку проекта в командой строке

3. Соберите проект
mvn clean package

4. Запустите приложение
java -jar kardo-0.0.1-SNAPSHOT.jar
