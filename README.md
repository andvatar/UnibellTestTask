REST API для создания и просмотра клиентов и их контактов.

Эндпоинты:

1. /client/create - создание нового клиента

    JSON - {

   "lastName": фамилия клиента (обязательно, макс длинна 100 символов),

   "firstName": имя клиента (обязательно, макс длинна 100 символов),

   "patronymic": отчество клиента (необязательно, макс длинна 100 символов)

}

2. /client/contact/create/{clientId} - создание нового контакта клиента

    JSON - {

   "type": PHONE/EMAIL (обязательно),
   
   "contact": номер телефона/EMAIL (обязательно, макс длинна 100 символов)

   } 

3. /client/getAll - получение списка клиентов
4. /client/get/{id} - получение клиента по id
5. /client/contact/get/{id} - получение списка контактов по id клиента
6. /client/contact/get/{id}/{type} - получение списка контактов по id клиента и типу контакта

