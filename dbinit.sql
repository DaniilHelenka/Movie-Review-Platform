--
-- PostgreSQL database dump
--

-- Dumped from database version 15.8
-- Dumped by pg_dump version 15.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: techtask
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO techtask;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: techtask
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: movies; Type: TABLE; Schema: public; Owner: techtask
--

CREATE TABLE public.movies (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    genre character varying(100),
    description text,
    release_date date,
    poster_url character varying(124)
);


ALTER TABLE public.movies OWNER TO techtask;

--
-- Name: movies_id_seq; Type: SEQUENCE; Schema: public; Owner: techtask
--

CREATE SEQUENCE public.movies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movies_id_seq OWNER TO techtask;

--
-- Name: movies_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: techtask
--

ALTER SEQUENCE public.movies_id_seq OWNED BY public.movies.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: techtask
--

CREATE TABLE public.reviews (
    id integer NOT NULL,
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    rating integer,
    comments text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT reviews_rating_check CHECK (((rating >= 1) AND (rating <= 10)))
);


ALTER TABLE public.reviews OWNER TO techtask;

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: techtask
--

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_id_seq OWNER TO techtask;

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: techtask
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: techtask
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    image character varying(124) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE public.users OWNER TO techtask;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: techtask
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO techtask;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: techtask
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: watchlist; Type: TABLE; Schema: public; Owner: techtask
--

CREATE TABLE public.watchlist (
    id integer NOT NULL,
    user_id integer NOT NULL,
    movie_id integer NOT NULL,
    list_type character varying(50) NOT NULL
);


ALTER TABLE public.watchlist OWNER TO techtask;

--
-- Name: watchlist_id_seq; Type: SEQUENCE; Schema: public; Owner: techtask
--

CREATE SEQUENCE public.watchlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.watchlist_id_seq OWNER TO techtask;

--
-- Name: watchlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: techtask
--

ALTER SEQUENCE public.watchlist_id_seq OWNED BY public.watchlist.id;


--
-- Name: movies id; Type: DEFAULT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.movies ALTER COLUMN id SET DEFAULT nextval('public.movies_id_seq'::regclass);


--
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Name: watchlist id; Type: DEFAULT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.watchlist ALTER COLUMN id SET DEFAULT nextval('public.watchlist_id_seq'::regclass);


--
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: techtask
--

COPY public.movies (id, name, genre, description, release_date, poster_url) FROM stdin;
17	Побег из Шоушенка	Драма	Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, обладающий живым умом и доброй душой, находит подход как к заключённым, так и к охранникам, добиваясь их особого к себе расположения.	1994-09-10	posters/Pobeg_iz_shoushenka.jpg
11	1+1	Драма, комедия	Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, – молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений.	2011-09-23	posters/1+1.jpg
12	Hancock	фантастика, боевик, драма, комедия	Есть герои, есть супергерои, и есть Хэнкок. Обладание сверхспособностями предполагает ответственность, все знают это — кроме него. За любую задачу он берётся с душой и лучшими намерениями, спасает жизни людей — ценой нечеловеческих разрушений и неисчислимого ущерба. В конце концов, терпение общественности подходит к концу: люди благодарны своему местному герою, но иногда не понимают, чем заслужили такое наказание.\n\nХэнкок не из тех парней, кого волнует какое-то там общественное мнение, но однажды, после очередного подвига, спасая высокопоставленного пиарщика Рэя, он вдруг понимает, что в некотором смысле он может быть уязвим. С этим трудно смириться, особенно по мнению Мэри, жены Рэя, считающей, что Хэнкок попросту безнадежен.	2008-06-16	posters/hancock.jpg
16	Alien	\nужасы, фантастика, триллер	В далеком будущем возвращающийся на Землю грузовой космический корабль перехватывает исходящий с неизвестной планеты сигнал. Экипаж, в соответствии с основными инструкциями, обязан найти и исследовать источник сигнала. Оказавшись на планете, астронавты повсюду обнаруживают предметы, по виду напоминающие гигантские коконы.	1979-05-25	posters/alien.jpg
14	Не время умирать	\nбоевик, триллер, приключения	Джеймс Бонд оставил оперативную службу и наслаждается спокойной жизнью на Ямайке. Все меняется, когда на острове появляется его старый друг Феликс Лейтер из ЦРУ с просьбой о помощи. Миссия по спасению похищенного ученого оказывается опаснее, чем предполагалось изначально. Бонд попадает в ловушку таинственного злодея, вооруженного опасным биологическим оружием.	2021-09-28	posters/No_Time_to_Die_poster.jpg
13	Легенда	\nкриминал, триллер, драма	Близнецы Реджи и Ронни Крэй — культовые фигуры преступного мира Великобритании 1960-х. Братья возглавляли самую влиятельную бандитскую группировку Ист-Энда. В их послужном списке были вооруженные грабежи, рэкет, поджоги, покушения, убийства и собственный ночной клуб, куда доезжали даже голливудские знаменитости. Среди их жертв — криминальные авторитеты Джек МакВитти и Джордж Корнелл.	2015-09-03	posters/legenda.jpg
\.


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: techtask
--

COPY public.reviews (id, user_id, movie_id, rating, comments, created_at) FROM stdin;
14	2	11	9	Nice film!	2024-12-09 17:04:52.079897
15	2	12	3	Bad film!(	2024-12-09 17:05:31.924634
16	2	13	10	adasdka psdjpa kd l[aksddk 1!	2024-12-09 17:08:43.692534
17	2	12	4	ЙЦуйцуйцу	2024-12-12 11:00:07.694479
18	2	17	10	Говоря про фильм «Побег из Шоушенка», то я всегда говорю себе, что главным героем здесь является Эллис Бойд Реддинг, которого сыграл талантливый Морган Фриман. Это точно история Реддинга, а не Энди Дюфрейна, потом что Энди не меняется на протяжении всего сюжета, он просто следует конкретной цели, просто чаще всего все действия, приводящие к конечной реализации, умело скрыты сценаристами и режиссером от наших глаз\r\n\r\nРеддинг - заключенный на пожизненном сроке, который уже смирился со своей судьбой, но внутренне к настоящему времени давно принял себя таким, какой он есть, и все свои деяния искупил за решеткой ввиду длительной изоляции. Он действительно предстает перед нами, как зрелый умный мужчина, понимающий, что такое хорошо и что такое плохо, он помогает нуждающимся, обходит стороной опасность\r\n\r\nНо в самом начале герой Фримана без толики надежды на какой-либо просвет уже по сути планирует в тюрьме и умереть, но именно Энди стал для него тем самым светом и дорогой в новый мир, в который не так страшно ступать, как кажется поначалу. Это точно история Эллиса Бойда Реддинга, тем более, что это его голос за кадром двигает сюжет в нужном направлении и общается со зрителем.	2024-12-12 12:54:03.968645
19	2	17	10	\r\n'Побег из Шоушенка' – фильм о классической истории дружбы, надежде и борьбе за выживание в тяжелых условиях тюремной жизни.\r\n\r\nНевозможно не говорить о прекрасном сюжете этого фильма. Энди Дюфрейн - банковский бухгалтер, который был незаслуженно приговорен к пожизненному заключению в тюрьме Шоушенк. Внутри тюрьмы Энди завоевывает уважение среди других заключенных своими финансовыми навыками и помогает им в различных финансовых и правовых вопросах.\r\n\r\nСамый главный акцент делается на дружбе между Энди и Редом. Энди мечтает о побеге и тайно работает над своими планами, в то время как Ред становится его ближайшим другом и поддержкой.\r\n\r\nПосле просмотра фильма ты понимаешь, насколько бывают разные люди. Некоторые себя только и видят в тюрьме, а выпускаясь в цивилизованный мир – они просто вешаются, и не знают, как себя вести и действовать в тех или иных ситуациях. Взяв того еже Реда, он даже в туалет просился, будучи на свободе.\r\n\r\nЯ не боюсь этого слова, но фильм действительно лучший их лучших не только в те года, а в принципе, учитывая даже современное кино. Заслуженный рейтинг.\r\n\r\nЕсли говорить об актёрах, больше всего мне понравился Тим Роббинс, сыгравший роль Энди Дюфрейна, банкира, который был неправомерно приговорен к пожизненному заключению в тюрьме Шоушенк. Мне кажется, это одна из лучших его ролей. И конечно же, это Морган Фриман, сыгравший роль Эллиса 'Рэда' Реддинга, заключенного, который был лидером в тюрьме и другом Анди.\r\n\r\nФильм безупречный, словами сложно передать те эмоции, которые испытываешь при просмотре.\r\n\r\n10 из 10	2024-12-12 12:54:30.147552
21	16	17	5	asdasdasda	2024-12-12 14:31:40.56097
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: techtask
--

COPY public.users (id, name, email, image, password, role) FROM stdin;
1	Daniil	daniilhelenka98@gmail.com	user/hancock.jpg	123123	ADMIN
2	Ivan	Ivan@mail.ru	users/alien.jpg	123123	ADMIN
14	Test	test@mail.ru	users/legenda.jpg	123123	ADMIN
15	test2	test2@mail.ru	users/1+1.jpg	123123	ADMIN
16	Супер Критик	kritik@mail.com	users/Avatarka_raketa.jpg	123123	ADMIN
17	User	user@mail.com	users/Pobeg_iz_shoushenka.jpg	123123	USER
\.


--
-- Data for Name: watchlist; Type: TABLE DATA; Schema: public; Owner: techtask
--

COPY public.watchlist (id, user_id, movie_id, list_type) FROM stdin;
13	16	17	watching
16	16	16	watched
15	16	12	watched
14	16	11	watched
\.


--
-- Name: movies_id_seq; Type: SEQUENCE SET; Schema: public; Owner: techtask
--

SELECT pg_catalog.setval('public.movies_id_seq', 17, true);


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: techtask
--

SELECT pg_catalog.setval('public.reviews_id_seq', 21, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: techtask
--

SELECT pg_catalog.setval('public.users_id_seq', 17, true);


--
-- Name: watchlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: techtask
--

SELECT pg_catalog.setval('public.watchlist_id_seq', 20, true);


--
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (id);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: watchlist uc_user_movie; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT uc_user_movie UNIQUE (user_id, movie_id, list_type);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: watchlist watchlist_pkey; Type: CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT watchlist_pkey PRIMARY KEY (id);


--
-- Name: watchlist fk_movie; Type: FK CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE;


--
-- Name: watchlist fk_user; Type: FK CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: reviews reviews_movie_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id);


--
-- Name: reviews reviews_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: techtask
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: techtask
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


--
-- PostgreSQL database dump complete
--

