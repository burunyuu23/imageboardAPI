INSERT INTO message_table (thread_id, body)
VALUES (1, 'Я всю жизнь любил цифры, да... ДА Я ИХ ПРОСТО ОБОЖАЮ, ' ||
           'КАЖДЫЙ ДЕНЬ ПИШУ ЧИСЛА, СОЧЕТАЮ ЦИФРЫ В ОГРОМНЫЕ ЧИСЛА ' ||
           'Я СЧИТАЮ КАЖДЫЙ ЧАС КАЖДУЮ МИНУТУ, В МАГАЗИНЕ Я ВСЕГДА ' ||
           'ПОДСЧИТЫВАЮ СТОИМОСТЬ ВПЛОТЬ ДО КОПЕЙКИ. ' ||
           'давайте ради моего хобби посчитаем от 0 до 2000? ' ||
           E'\n' || 'Я начну. ' || E'\n\n' || '# 0');

DO
$$
    DECLARE
        i INT;
    BEGIN
        i := 1;
        WHILE i <= 2000
            LOOP
                INSERT INTO message_table (thread_id, body)
                VALUES (1, CASE
                            WHEN RANDOM() < 0.5 THEN CONCAT('А мне нравится число: ', i)
                            ELSE CONCAT('Привет вот моё число: ', i)
                    END);
                INSERT INTO reply_table (reply_id, msg_id)
                VALUES (1, i+1);
                i := i + 1;
            END LOOP;
    END
$$;

INSERT INTO reply_table (reply_id, msg_id)
VALUES (2, 3);
INSERT INTO reply_table (reply_id, msg_id)
VALUES (2, 4);

INSERT INTO attachment_table (msg_id, attachment)
VALUES
    (3, pg_read_binary_file('/var/lib/docker/volumes/banners/h.jpg')),
    (3, pg_read_binary_file('/var/lib/docker/volumes/banners/b.jpg'))