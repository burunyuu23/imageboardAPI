-- Заполняем таблицу theme_table (продолжение)
INSERT INTO theme_table (name, description)
VALUES
    ('Разное', 'В основном флуд ни о чём и обо всём.'),
    ('Техника', 'Технологические новинки, технические новинки, программные... и ЭВМ!'),
    ('Мир', 'Обсуждение общих вопросов и тем, которые происходят в мире.'),
    ('Наука', 'Там очень умные люди обсуждают что-то очень умное.'),
    ('Творчество', 'Штуки генерирующиеся фантазией.'),
    ('Жизнь', 'Реальная жизнь и люди.');



-- Разное
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('h', 'Помощь', 'Здесь вы можете получить помощь и советы.', 1, pg_read_binary_file('/var/lib/docker/volumes/banners/h.jpg')),
    ('b', 'Бред', 'Бред. Полный, несвязный, неимоверный и беспощадный.', 1, pg_read_binary_file('/var/lib/docker/volumes/banners/b.jpg'));


-- Техника
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('tech', 'Технологии', 'Обсуждение современных технологий.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/tech.jpg')),
    ('comp', 'Компьютеры', 'Смена винды, процессора, видюхи, а заодно и перепрошивка биоса.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/comp.jpg')),
    ('mob', 'Мобильные устройства', 'Телефоны, КПК, смартфоны и многое другое.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/mob.jpg')),
    ('gadg', 'Полезные гаджеты', 'Обсуждение нестандартных, но полезных технологических приспособлений.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/gadg.jpg')),
    ('cr', 'Криптовалюты', 'Купил по 40, продал по 25.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/cr.jpg')),
    ('mlneu', 'Машинное обучение и нейронные сети', 'Учим машины думать.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/mlneu.jpg')),
    ('ai', 'Искусственный интеллект', 'Может ли робот сочинить симф... Ой.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/ai.jpg')),
    ('vr', 'Виртуальная реальность', 'Выпадаем из реальности.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/vr.jpg')),
    ('ar', 'Дополненная реальность', 'Дополняем реальность.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/ar.jpg')),
    ('weap', 'Оружие', 'Пушки.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/weap.jpg')),
    ('wm', 'Военная техника', 'Танки, самолёты, вертолёты и корабль.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/wm.jpg')),
    ('cars', 'Автомобили', 'Тачки и их прокачки.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/cars.jpg')),
    ('prg', 'Программирование', '0.1+0.2=0.3?', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/prg.jpg')),
    ('os', 'Операционные системы', 'Новый дистрибутив линукса ещё никогда не был таким новым...', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/os.jpg')),
    ('techsup', 'Техническая поддержка', 'Помощь в решении технических проблем.', 2, pg_read_binary_file('/var/lib/docker/volumes/banners/techsup.jpg'));


-- Мир
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('p', 'Политика', 'Пожалуйста только о политике.', 3, pg_read_binary_file('/var/lib/docker/volumes/banners/p.jpg')),
    ('w', 'Мир', 'Общемировые тенденции и почему где-то лучше, чем здесь.', 3, pg_read_binary_file('/var/lib/docker/volumes/banners/w.jpg')),
    ('c', 'Страны', 'Почему X страна такая, а Y не такая.', 3, pg_read_binary_file('/var/lib/docker/volumes/banners/c.jpg')),
    ('nw', 'Новости', 'Здесь события интереснее, чем в книгах.', 3, pg_read_binary_file('/var/lib/docker/volumes/banners/nw.jpg'));


-- Наука
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('hi', 'История', 'А вот Ленин в своё время сказал одну такую фразу...', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/hi.jpg')),
    ('sp', 'Космос', 'А что если космос - существо?.', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/sp.jpg')),
    ('psy', 'Психология', 'Психопаты и не только.', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/psy.jpg')),
    ('phil', 'Философия', 'Быть или очень быть.', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/phil.jpg')),
    ('math', 'Математика', '0.1+0.2=0.3!', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/math.jpg')),
    ('lang', 'Языкознание', 'Быть или очень быть.', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/lang.jpg')),
    ('sci', 'Наука', 'Обсуждение научных открытий.', 4, pg_read_binary_file('/var/lib/docker/volumes/banners/sci.jpg'));


-- Творчество
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('art', 'Искусство', 'Искусство и культура.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/art.jpg')),
    ('g', 'Игры', 'Компьютерные, настольные.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/g.jpg')),
    ('arg', 'АРГ', 'Игры альтернативной реальности.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/arg.jpg')),
    ('mov', 'Кино', 'Фильмы и кинематография.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/mov.jpg')),
    ('mus', 'Музыка', 'Музыка и концерты.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/mus.jpg')),
    ('lost', 'Лостмедиа', 'Ищем потерянные медиа.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/lost.jpg')),
    ('fd', 'Еда', 'Рецепты и обсуждение кулинарии.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/fd.jpg')),
    ('bk', 'Книги', 'Книги и литература.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/bk.jpg')),
    ('hob', 'Хобби', 'Хобби и увлечения.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/hob.jpg')),
    ('fsh', 'Мода', 'Тенденции моды и стиль.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/fsh.jpg'));


-- Жизнь
INSERT INTO board_table (id, name, description, theme_id, banner)
VALUES
    ('soc', 'Общество', 'Мы живём в обществе.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/soc.jpg')),
    ('sport', 'Спорт', 'Олимпиады, турниры, спорт.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/sport.jpg')),
    ('fit', 'Фитнес', 'Бегит прес качат мышц мышц.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/fit.jpg')),
    ('travel', 'Путешествия', 'Другие миры, вселенные, галактики, ну или хотя бы красивый пейзаж.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/travel.jpg')),
    ('out', 'Активный отдых', 'Всё про активный отдых.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/out.jpg')),
    ('heal', 'Здоровье', 'Советы по здоровью и медицине.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/heal.jpg')),
    ('life', 'Истории из жизни', 'Рассказывайте о себе и получайте отклик.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/life.jpg')),
    ('work', 'Работа', 'Коллеги - ангелочки, заказчики - милашки, а начальник - вообще сказка.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/work.jpg')),
    ('gard', 'Сад и огород', 'Советы по садоводству и огородничеству.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/gard.jpg')),
    ('pet', 'Домашние животные', 'Обсуждение ухода за домашними животными.', 6, pg_read_binary_file('/var/lib/docker/volumes/banners/pet.jpg')),
    ('homei', 'Улучшение дома', 'Советы по ремонту и улучшению дома.', 5, pg_read_binary_file('/var/lib/docker/volumes/banners/homei.jpg'));
