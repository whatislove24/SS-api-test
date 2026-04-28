 API Tests (SimbirSoft Test Task)

 Описание
Набор автоматизированных тестов для REST API сервиса.

Покрыты основные CRUD операции:
- создание сущности (POST)
- получение сущности по ID (GET)
- получение списка сущностей (GET ALL)
- удаление сущности (DELETE)
- обновление сущности (PATCH)


 Стек технологий
- Java 17
- Gradle
- RestAssured
- JUnit 5


 Запуск проекта

 1. Клонирование репозитория

git clone https://github.com/whatislove24/SS-api-test
cd ss.api-tests
 
 2. Запуск сервиса и базы данных
cd test-service
docker-compose up --build -d

Проверка:

docker ps

Сервис должен быть доступен по адресу:

http://localhost:8080
3. Запуск тестов
Windows:
cd ..
.\gradlew test

API

Базовый URL:

http://localhost:8080

Swagger документация:

http://localhost:8080/api/_/docs/swagger/

Покрытие тестами

Реализованы тесты:

Create — создание сущности

GetById — получение по ID

GetAll — получение списка

Delete — удаление сущности

Patch — обновление 

