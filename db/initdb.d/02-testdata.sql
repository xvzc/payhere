USE `payhere`;

-- password: 1234
INSERT INTO `user` (`email`, `password`)
VALUES
    ('user1@gmail.com', '$2a$10$7ck29jxourdeGHXGguaG9eep6pI8mixl4DD2MD6UzjXWxYTX52dRK'),
    ('user2@gmail.com', '$2a$10$7ck29jxourdeGHXGguaG9eep6pI8mixl4DD2MD6UzjXWxYTX52dRK'),
    ('user3@gmail.com', '$2a$10$7ck29jxourdeGHXGguaG9eep6pI8mixl4DD2MD6UzjXWxYTX52dRK');

INSERT INTO `tag` (`user_id`, `name`)
VALUES
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '식비'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '쇼핑'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '술'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '데이트'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '수영강습료'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), '용돈'),

    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '밥'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '간식'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '주유'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '월급'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '병원'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '보험료'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), '교통비'),

    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), '치과'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), '햇반1박스'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), '겨울옷'),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), '에어팟프로');

INSERT INTO `receipt` (`user_id`, `amount`, `tag_id`, `description`, `created`)
VALUES
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-01 12:12:08 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -7500,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-02 12:32:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -8000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-03 11:45:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6500,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-07 12:35:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-09 13:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '짜장면 먹음', ' 2021-11-10 12:23:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -7500,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-15 13:48:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-20 12:28:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -8000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-22 11:23:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -10000,  (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-23 12:22:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -6500,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-27 13:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -7000,   (SELECT `id` FROM `tag` WHERE `name` = '식비'), '', ' 2021-11-28 12:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '술'), '친구랑 삼겹살 먹음', ' 2021-11-01 19:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -25000,  (SELECT `id` FROM `tag` WHERE `name` = '술'), '', ' 2021-11-08 20:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -32500,  (SELECT `id` FROM `tag` WHERE `name` = '술'), '', ' 2021-11-18 19:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '술'), '', ' 2021-11-22 21:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), 500000,  (SELECT `id` FROM `tag` WHERE `name` = '용돈'), '', ' 2021-11-10 10:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '데이트'), '', ' 2021-11-02 10:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '데이트'), '', ' 2021-11-16 18:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user1@gmail.com'), -100000, (SELECT `id` FROM `tag` WHERE `name` = '수영강습료'), '', ' 2021-11-16 18:38:58 '),

    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-01 12:12:08 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -7500,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-02 12:32:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -8000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-03 11:45:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6500,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-07 12:35:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-09 13:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-10 12:23:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -7500,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-15 13:48:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-20 12:28:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -6500,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-27 13:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -7000,   (SELECT `id` FROM `tag` WHERE `name` = '밥'),  '', ' 2021-11-28 12:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -8000,   (SELECT `id` FROM `tag` WHERE `name` = '간식'), '', ' 2021-11-22 11:23:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -10000,  (SELECT `id` FROM `tag` WHERE `name` = '간식'), '', ' 2021-11-23 12:22:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '주유'), '', ' 2021-11-01 19:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -25000,  (SELECT `id` FROM `tag` WHERE `name` = '주유'), '', ' 2021-11-08 20:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -32500,  (SELECT `id` FROM `tag` WHERE `name` = '주유'), '', ' 2021-11-18 19:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '주유'), '', ' 2021-11-22 21:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), 3000000, (SELECT `id` FROM `tag` WHERE `name` = '월급'), '', ' 2021-11-10 10:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '병원'), '', ' 2021-11-02 10:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -30000,  (SELECT `id` FROM `tag` WHERE `name` = '병원'), '', ' 2021-11-16 18:38:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user2@gmail.com'), -100000, (SELECT `id` FROM `tag` WHERE `name` = '보험료'), '', ' 2021-11-16 18:38:58 '),

    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '햇반1박스'), '', ' 2021-11-09 13:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), -6000,   (SELECT `id` FROM `tag` WHERE `name` = '햇반1박스'), '', ' 2021-11-30 13:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), -100000, (SELECT `id` FROM `tag` WHERE `name` = '치과'), '', ' 2021-11-22 11:23:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), -250000, (SELECT `id` FROM `tag` WHERE `name` = '에어팟프로'), '', ' 2021-11-01 19:18:58 '),
    ((SELECT `id` FROM `user` WHERE `email` = 'user3@gmail.com'), -100000, (SELECT `id` FROM `tag` WHERE `name` = '겨울옷'), '', ' 2021-11-08 20:18:58 ');
