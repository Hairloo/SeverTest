Тестовое задание - "Приемка поставок" позволяет создать поставку и генерировать отчеты по поставкам в опеределенный период даты<br />
для функционирования работы необходимо, чтобы в таблицах products и suppliers были статические данные о поставляемых продуктах и поставщиках соответственно<br />
вся остальная информация добавляется в бд динамически с помощью действия пользователя<br />
домашняя страница имеет path -> /home и имеет возможность переходить на страницы создания поставки и генерации отчета<br />
страница создать поставку имеет path -> /delivery/create и перенаправляет пользователя на домашнюю страницу после указания всех данных<br />
страница сформировать отчет имеет path -> /report/generate и перенаправляет пользователя на страницу с отчетом с path /report/get с указанными requestparam<br /> 
Реализовано на Java, Spring Boot, Postgres<br />
