INSERT INTO users(name) VALUES ('Christine Daae');
INSERT INTO users(name) VALUES ('Lisbeth Salander');
INSERT INTO users(name) VALUES ('Gregory House');
INSERT INTO users(name) VALUES ('Will Graham');
INSERT INTO users(name) VALUES ('Clarice Starling');

INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (4, 'USD', 'BRL', 50.00, 5.30,   '2021-05-19 10:50:37');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (2, 'JPY', 'BRL', 63.37, 0.049,  '2021-05-15 11:05:28');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (3, 'EUR', 'USD', 10.03, 1.22,   '2021-05-14 13:22:56');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (1, 'EUR', 'JPY', 80.68, 132.88, '2021-05-18 15:29:44');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (5, 'EUR', 'CAD', 40.85, 1.47,   '2021-05-20 00:37:48');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (4, 'EUR', 'DJF', 13.07, 217.26, '2021-05-20 20:45:07');
INSERT INTO transactions(user_id, currency_origin, currency_target, value, exchange_rate, date) VALUES (2, 'EUR', 'USD', 38.40, 1.22,   '2021-05-21 21:40:11');