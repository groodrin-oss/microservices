👋 Добро пожаловать!

Если ты только что склонировал этот репозиторий и не понимаешь, что здесь происходит — не переживай 😊
Это учебный/демо-проект с двумя микросервисами:
    HR-сервис — хранит и управляет кадровыми данными сотрудников
    Payroll-сервис — хранит их дубликат и получает обновления от HR
    Проект показывает, как можно синхронизировать данные между двумя независимыми микросервисами через REST API.

Что внутри

    Два микросервиса на Spring Boot
    Две БД на PostgreSQL
    Сборка и запуск через Docker Compose
    Простой REST-API с CRUD операциями для HR и синхронизацией в Payroll
    Готовые команды для быстрого старта 

Что тебе нужно

    Java 17+
    Maven
    Docker + Docker Compose



Клонируем репозиторий
    ```
    git clone https://github.com/groodrin-oss/microservices.git
    ```

Поднимаем всю инфраструктуру
    ```
    docker-compose up --build
    ```

После успешного запуска:

HR сервис: http://localhost:8080

Payroll сервис: http://localhost:8081


HR Service — http://localhost:8080

    Метод	URL	                Описание	
    GET	    /employees	        Получить список всех сотрудников из кадровой БД	
    GET	    /employees/{id}	    Получить информацию о конкретном сотруднике	
    POST	/employees	        Создать сотрудника и отправить событие в Payroll
    PUT	    /employees/{id}	    Обновить данные сотрудника и отправить событие в Payroll
    DELETE	/employees/{id}	    Удалить сотрудника и отправить событие в Payroll

Пример запроса POST
POST http://localhost:8080/employees
    ```
    {
    "id": 1,
    "firstName": "Иван",
    "lastName": "Иванов",
    "position": "Разработчик",
    "salary": 100000
    }
    ```

Пример запроса PUT
POST http://localhost:8080/employees/1
    ```
    {
    "firstName": "Иван",
    "lastName": "Иванов",
    "position": "Разработчик",
    "salary": 100000
    }
    ```

Payroll Service — http://localhost:8081
    Метод   URL	            Описание	
    GET     /employees  	Получить список всех сотрудников из расчётной БД
    GET	    /employees/{id}	Получить конкретного сотрудника из расчётной БД

Ручная проверка
# HR DB
    ```
    docker exec -it hr-db psql -U hr_user -d hr
    ```
    ```
    SELECT * FROM employee;
    ```


# Payroll DB
    ```
    docker exec -it payroll-db psql -U payroll_user -d payroll
    ```
    ```
    SELECT * FROM employee;
    ```