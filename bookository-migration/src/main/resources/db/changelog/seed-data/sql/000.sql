INSERT INTO bookository.book (name, genre, annotation, is_available, language, created_at, updated_at)
VALUES ('Мастер и Маргарита',
        'Роман',
        'Роман Михаила Афанасьевича Булгакова, работа над которым началась, по одним данным, в 1928 году, по другим — в 1929-м и продолжалась вплоть до смерти писателя в марте 1940 года. Роман относится к незавершённым произведениям; редактирование и сведение воедино черновых записей осуществляла после смерти мужа вдова писателя — Елена Сергеевна. Первоначальная версия произведения, задуманного как «роман о дьяволе»[1], имела рабочее название «Копыто инженера»[2][3][комм. 1] и была уничтожена Булгаковым в 1930 году. В последующих редакциях среди героев произведения появились автор романа о Понтии Пилате и его возлюбленная. Окончательное название — «Мастер и Маргарита» — закрепилось в 1937 году.',
        TRUE,
        'RU'::bookository.language, NOW(),
        NULL),

       ('Война и мир',
        'Роман',
        'Роман-эпопея Льва Николаевича Толстого, описывающий русское общество в эпоху войн против Наполеона в 1805—1812 годах. Эпилог романа доводит повествование до 1820 года.',
        TRUE,
        'RU'::bookository.language,
        NOW(),
        NULL),

       ('Капитанская дочка',
        'Исторический роман',
        'исторический роман[K 1] (или повесть) Александра Пушкина, действие которого происходит во время восстания Емельяна Пугачёва. Впервые опубликован без указания имени автора в 4-й книжке журнала «Современник», поступившей в продажу в последней декаде 1836 года[2].',
        TRUE,
        'RU'::bookository.language,
        NOW(),
        NULL),

       ('Три мушкетера',
        'Роман-фельетон и приключения',
        ' историко-приключенческий роман Александра Дюма-отца, впервые опубликованный в парижской газете Le Siècle[фр.] в 1844 году с 14 марта по 11 июля. Книга посвящена приключениям молодого дворянина по имени д’Артаньян, отправившегося в Париж, чтобы стать мушкетёром, и трёх его друзей-мушкетёров Атоса, Портоса и Арамиса в период между 1625 и 1628 годами.

История д’Артаньяна продолжается в двух других романах трилогии: «Двадцать лет спустя» и «Виконт де Бражелон, или Десять лет спустя».',
        TRUE,
        'EN'::bookository.language,
        NOW(),
        NULL),

       ('Айвенго',
        'Исторический роман',
        '«Айвенго» — первый роман Скотта, действие которого происходит за пределами Шотландии. События приурочены к 1194 году — через 128 лет после битвы при Гастингсе, в результате которой англосаксы были покорены норманнами.',
        TRUE,
        'EN'::bookository.language,
        NOW(),
        NULL)
;