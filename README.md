Тестовое задание - "Приемка поставок" позволяет создать поставку и генерировать отчеты по поставкам в опеределенный период даты__
для функционирования работы необходимо, чтобы в таблицах products и suppliers были статические данные о поставляемых продуктах и поставщиках соответственно__
вся остальная информация добавляется в бд динамически с помощью действия пользователя__
домашняя страница имеет path -> /home и имеет возможность переходить на страницы создания поставки и генерации отчета__
страница создать поставку имеет path -> /delivery/create и перенаправляет пользователя на домашнюю страницу после указания всех данных__
страница сформировать отчет имеет path -> /report/generet и перенаправляет пользователя на страницу с отчетом с path /report/get с указанными requestparam__ 
Реализовано на Java, Spring Boot, Postgres__
