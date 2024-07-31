CREATE TABLE IF NOT EXISTS country (
                           id int NOT NULL,
                           name varchar(50) NOT NULL,
                           CONSTRAINT pk_country PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS region (
                          id int NOT NULL,
                          name varchar(50) NOT NULL,
                          country_id int NOT NULL,
                          CONSTRAINT pk_region PRIMARY KEY (id),
                          CONSTRAINT country_id_to_region FOREIGN KEY(country_id) REFERENCES country (id)
);

CREATE TABLE IF NOT EXISTS photo (
                         id bigint   NOT NULL,
                         link varchar   NOT NULL,
                         author_id int NOT NULL,
                         created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
                         CONSTRAINT pk_photo PRIMARY KEY (id),
                         CONSTRAINT author_users_id FOREIGN KEY(author_id)
                         REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS users (
                         id bigint   NOT NULL,
                         surname varchar(50)   NOT NULL,
                         name varchar(50)   NOT NULL,
                         patronymic varchar(50)   NOT NULL,
                         type varchar(10)   NOT NULL,
                         email varchar(50)   NOT NULL,
                         password varchar(100)   NOT NULL,
                         birthday timestamp   NOT NULL,
                         country_id int   NOT NULL,
                         region_id int   NOT NULL,
                         city varchar(50)   NOT NULL,
                         link varchar(100)   NOT NULL,
                         phone varchar(20)   NOT NULL,
                         gender varchar(10)   NOT NULL,
                         nationality varchar(50)   NOT NULL,
                         photo_id int   NOT NULL,
                         portfolio varchar(100)   NOT NULL,
                         description varchar(500)   NOT NULL,
                         CONSTRAINT pk_users PRIMARY KEY (id),
                         CONSTRAINT uc_users_email UNIQUE (email),
                         CONSTRAINT users_id_to_country FOREIGN KEY(country_id)
                         REFERENCES country (id),
                         CONSTRAINT users_id_to_region FOREIGN KEY(region_id)
                         REFERENCES region (id),
                         CONSTRAINT users_photo_id FOREIGN KEY(photo_id)
                         REFERENCES photo (id)
);

CREATE TABLE IF NOT EXISTS direction (
                             id int   NOT NULL,
                             name varchar(20)   NOT NULL,
                             CONSTRAINT pk_direction PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS location (
                            id int   NOT NULL,
                            lat double precision   NOT NULL,
                            lon double precision   NOT NULL,
                            CONSTRAINT pk_location PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS comments (
                            id bigint   NOT NULL,
                            text varchar(500) NOT NULL,
                            photo_id int,
                            author_id int,
                            created timestamp   NOT NULL,
                            updated timestamp   NOT NULL,
                            CONSTRAINT pk_comment PRIMARY KEY (id),
                            CONSTRAINT comments_photo_id FOREIGN KEY(photo_id)
                            REFERENCES photo (id),
                            CONSTRAINT comments_user_id FOREIGN KEY(author_id)
                            REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS events (
                          id bigint   NOT NULL,
                          name varchar(100)   NOT NULL,
                          type varchar(20)   NOT NULL,
                          direction_id int   NOT NULL,
                          date timestamp   NOT NULL,
                          description varchar(500)   NOT NULL,
                          location int   NOT NULL,
                          time varchar   NOT NULL,
                          CONSTRAINT pk_events PRIMARY KEY (id),
                          CONSTRAINT events_direction_id FOREIGN KEY(direction_id)
                             REFERENCES direction (id),
                          CONSTRAINT  events_location FOREIGN KEY(location)
                            REFERENCES location (id)

);

CREATE TABLE IF NOT EXISTS partners (
                            id bigint   NOT NULL,
                            photo_id bigint   NOT NULL,
                            name varchar(20)   NOT NULL,
                            type varchar(20)   NOT NULL,
                            description varchar(200)   NOT NULL,
                            link varchar   NOT NULL,
                            CONSTRAINT pk_partners PRIMARY KEY (id),
                            CONSTRAINT partners_photo_id FOREIGN KEY(photo)
                            REFERENCES photo (id)
);

--CREATE TABLE IF NOT EXISTS "News" (
--                        "id" bigint   NOT NULL,
--                        "photo" int   NOT NULL,
--                        "name" varchar(50)   NOT NULL,
--                        "description" varchar(50)   NOT NULL,
--                        "published_date" timestamp   NOT NULL,
--                        CONSTRAINT "pk_News" PRIMARY KEY (
--                                                          "id"
--                            ),
--                        CONSTRAINT news_photo FOREIGN KEY(photo)
--                       REFERENCES photo (id)
--);

CREATE TABLE IF NOT EXISTS stages (
                         id int   NOT NULL,
                         name varchar(20)   NOT NULL,
                         start_date timestamp   NOT NULL,
                         end_date timestamp   NOT NULL,
                         status varchar   NOT NULL,
                         CONSTRAINT pk_stages PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS competitions (
                               id bigint   NOT NULL,
                               name varchar(20)   NOT NULL,
                               description varchar(500)   NOT NULL,
                               direction_id int   NOT NULL,
                               stage_id int   NOT NULL,
                               CONSTRAINT pk_competitions PRIMARY KEY (id),
                               CONSTRAINT competition_direction_id FOREIGN KEY(direction_id)
                                       REFERENCES direction (id),
                               CONSTRAINT competition_stage_id FOREIGN KEY(stage_id)
                                           REFERENCES stages (id)
);

CREATE TABLE IF NOT EXISTS participants (
                                id bigint   NOT NULL,
                                user_id int   NOT NULL,
                                competition_id int   NOT NULL,
                                direction_id int   NOT NULL,
                                user_status varchar   NOT NULL,
                                CONSTRAINT pk_participants PRIMARY KEY (id),
                               CONSTRAINT participants_user_id FOREIGN KEY(user_id)
                                        REFERENCES "Users" ("id"),
                               CONSTRAINT participants_competition_id FOREIGN KEY(competition_id)
                                            REFERENCES competition (id),
                                CONSTRAINT participants_direction_id FOREIGN KEY(direction_id)
                                                REFERENCES direction (id)
);

--ALTER TABLE region ADD CONSTRAINT fk_region_country_id FOREIGN KEY(country_id)
--    REFERENCES country (id);

--ALTER TABLE users ADD CONSTRAINT fk_users_country_id FOREIGN KEY(country_id)
--    REFERENCES country (id);
--
--ALTER TABLE users ADD CONSTRAINT fk_users_region_id FOREIGN KEY(region_id)
--    REFERENCES region (id);

--ALTER TABLE users ADD CONSTRAINT fk_users_photo_id FOREIGN KEY(photo_id)
--    REFERENCES photo (id);

--ALTER TABLE events ADD CONSTRAINT fk_events_direction_id FOREIGN KEY(direction_id)
--    REFERENCES direction (id);

--ALTER TABLE events ADD CONSTRAINT fk_events_location FOREIGN KEY(location)
--    REFERENCES location (id);

--ALTER TABLE partners ADD CONSTRAINT fk_partners_icon_id FOREIGN KEY(icon_id)
--    REFERENCES photo (id);

--ALTER TABLE news ADD CONSTRAINT fk_news_photo FOREIGN KEY(photo)
--    REFERENCES photo (id);


--ALTER TABLE competition ADD CONSTRAINT fk_competition_direction_id FOREIGN KEY(direction_id)
--    REFERENCES direction (id);

--ALTER TABLE competition ADD CONSTRAINT fk_competition_stage_id FOREIGN KEY(stage_id)
--    REFERENCES stage (id);

--ALTER TABLE participants ADD CONSTRAINT fk_participants_user_id FOREIGN KEY(user_id)
--    REFERENCES "Users" ("id");

--ALTER TABLE participants ADD CONSTRAINT fk_participants_competition_id FOREIGN KEY(competition_id)
--    REFERENCES competition (id);

--ALTER TABLE participants ADD CONSTRAINT fk_participants_direction_id FOREIGN KEY(direction_id)
--    REFERENCES direction (id);