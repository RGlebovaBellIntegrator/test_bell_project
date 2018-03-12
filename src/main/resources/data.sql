INSERT INTO House (id, version, address) VALUES (1, 0, 'ул.Цюрупы, 16');

INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'Пётр', 20, 1);

INSERT INTO Person (version, first_name, age, house_id) VALUES (0, 'John', 25, 1);

--Добавление тестовых данных для домашнего задания

INSERT INTO Doc (code, name) VALUES (21, 'Паспорт');
INSERT INTO Doc (code, name) VALUES (7, 'Военный билет');
INSERT INTO Doc (code, name) VALUES (3, 'Свидетельство о рождении');
INSERT INTO Doc (code, name) VALUES (10, 'Паспорт иностранного гражданина');

INSERT INTO Country (code, name) VALUES (643, 'Российская Федерация');
INSERT INTO Country (code, name) VALUES (031, 'Республика Азербайджан');
INSERT INTO Country (code, name) VALUES (208, 'Королевство Дания');
INSERT INTO Country (code, name) VALUES (178, 'Республика Конго');
INSERT INTO Country (code, name) VALUES (434, 'Ливия');

INSERT INTO Organization (name, fullname, inn, kpp, address, phone)
VALUES ('Bell Integrator', 'АКЦИОНЕРНОЕ ОБЩЕСТВО "БЭЛЛ ИНТЕГРАТОР"', '7733180847', '502401001', 'Россия, Пенза, Московская улица, 27' , '+7 (495) 980-61-85');
INSERT INTO Organization (name, fullname, inn, kpp, address, phone)
VALUES ('Лента', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "ЛЕНТА"', '7814148471', '785050001', 'Санкт-Петербург г, ул.Савушкина, д.112, лит.Б, 197374' , '(812)3806159');
INSERT INTO Organization (name, fullname, inn, kpp, address, phone, is_active)
VALUES ('Тортики', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "ТОРТИКИ"', '7714158421', '568050001', 'Россия, Несуществующий город, д. Х' , '(8412)809080', 0);

INSERT INTO Office (org_id, name, phone, address, is_active) VALUES (1, 'Офис 54', '44-44-44', 'Московская улица, 27, 6 этаж', 0);
INSERT INTO Office (org_id, name, phone, address) VALUES (1, 'Офис 61', '44-44-50', 'Московская улица, 27, 6 этаж');
INSERT INTO Office (org_id, name, phone, address) VALUES (1, 'Офис 30', '44-44-50', 'Московская улица, 27, 3 этаж');
INSERT INTO Office (org_id, name, phone, address) VALUES (2, 'Офис 3', '77-13-50', 'ул.Савушкина, д.112, 3 этаж');
INSERT INTO Office (org_id, name, phone, address) VALUES (2, 'Офис 4', NULL, 'ул.Савушкина, д.112, 3 этаж');

INSERT INTO User (login, password, name) VALUES ('John', 'Constantine', 'Константин');
INSERT INTO User (login, password, name) VALUES ('Anna', 'qwerty123', 'Анна1');
INSERT INTO User (login, password, name) VALUES ('Anna_123', 'qwerty123', 'Анна');
INSERT INTO User (login, password, name) VALUES ('John_1', 'Constantine_1', 'Константин1');
INSERT INTO User (login, password, name) VALUES ('John_2', 'Constantine_1', 'Константин2');


INSERT INTO Employe (firstName, secondName, middleName, position_statement, phone, office_id, doc_id,
                    doc_number, doc_date, countrie_id)
            VALUES ('Джон', 'Константин', 'Александрович', 'Экзорцист', '666', 4, 1,
                    '4444 777777', '2015-12-03', 5);
INSERT INTO Employe (firstName, secondName, middleName, position_statement, phone, office_id, doc_id,
                    doc_number, doc_date, countrie_id)
            VALUES ('Иван', 'Иванов', 'Иванович', 'Директор', '001', 2, 3,
                    '1234 567890', '2006-01-01', 1);
INSERT INTO Employe (firstName, secondName, position_statement, office_id, doc_id,
                    doc_number, doc_date, countrie_id)
            VALUES ('Анна', 'Иванова', 'Учительница', 4, 2,
                    '5555 777777', '2014-04-04', 2);
INSERT INTO Employe (firstName, secondName, position_statement, phone, office_id, doc_id,
                    doc_number, doc_date, countrie_id, is_identified)
            VALUES ('Анна', 'Иванова', 'Художник', '123-123', 1, 4,
                    '4444 777777', '2015-12-03', 3, 0);
INSERT INTO Employe (firstName, secondName, middleName, position_statement, phone, office_id, doc_id,
                    doc_number, doc_date, countrie_id)
            VALUES ('Джон', 'Константин', 'Александрович', 'Экзорцист2', '666-1', 2, 1,
                    '4444 777777', '2015-07-23', 4);