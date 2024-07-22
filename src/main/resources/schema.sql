CREATE TABLE "Country" (
                           "id" int   NOT NULL,
                           "name" varchar(50)   NOT NULL,
                           CONSTRAINT "pk_Country" PRIMARY KEY (
                                                                "id"
                               )
);

CREATE TABLE "Region" (
                          "id" int   NOT NULL,
                          "name" varchar(50)   NOT NULL,
                          "country_id" int   NOT NULL,
                          CONSTRAINT "pk_Region" PRIMARY KEY (
                                                              "id"
                              )
);

CREATE TABLE "Photo" (
                         "id" bigint   NOT NULL,
                         "link" varchar   NOT NULL,
                         CONSTRAINT "pk_Photo" PRIMARY KEY (
                                                            "id"
                             )
);

CREATE TABLE "Users" (
                         "id" bigint   NOT NULL,
                         "surname" varchar(50)   NOT NULL,
                         "name" varchar(50)   NOT NULL,
                         "patronymic" varchar(50)   NOT NULL,
                         "type" varchar(10)   NOT NULL,
                         "email" varchar(50)   NOT NULL,
                         "password" varchar(100)   NOT NULL,
                         "birthday" timestamp   NOT NULL,
                         "country_id" int   NOT NULL,
                         "region_id" int   NOT NULL,
                         "city" varchar(50)   NOT NULL,
                         "link" varchar(100)   NOT NULL,
                         "phone" varchar(20)   NOT NULL,
                         "gender" varchar(10)   NOT NULL,
                         "nationality" varchar(50)   NOT NULL,
                         "photo_id" int   NOT NULL,
                         "portfolio" varchar(100)   NOT NULL,
                         "description" varchar(500)   NOT NULL,
                         CONSTRAINT "pk_Users" PRIMARY KEY (
                                                            "id"
                             ),
                         CONSTRAINT "uc_Users_email" UNIQUE (
                                                             "email"
                             )
);

CREATE TABLE "Direction" (
                             "id" int   NOT NULL,
                             "name" varchar(20)   NOT NULL,
                             CONSTRAINT "pk_Direction" PRIMARY KEY (
                                                                    "id"
                                 )
);

CREATE TABLE "Location" (
                            "id" int   NOT NULL,
                            "lat" double precision   NOT NULL,
                            "lon" double precision   NOT NULL,
                            CONSTRAINT "pk_Location" PRIMARY KEY (
                                                                  "id"
                                )
);

CREATE TABLE "Events" (
                          "id" bigint   NOT NULL,
                          "name" varchar(100)   NOT NULL,
                          "type" varchar(20)   NOT NULL,
                          "direction_id" int   NOT NULL,
                          "date" timestamp   NOT NULL,
                          "description" varchar(500)   NOT NULL,
                          "location" int   NOT NULL,
                          "time" varchar   NOT NULL,
                          CONSTRAINT "pk_Events" PRIMARY KEY (
                                                              "id"
                              )
);

CREATE TABLE "Partners" (
                            "id" bigint   NOT NULL,
                            "icon_id" int   NOT NULL,
                            "name" varchar(20)   NOT NULL,
                            "type" varchar(20)   NOT NULL,
                            "description" varchar(200)   NOT NULL,
                            "link" varchar   NOT NULL,
                            CONSTRAINT "pk_Partners" PRIMARY KEY (
                                                                  "id"
                                )
);

CREATE TABLE "News" (
                        "id" bigint   NOT NULL,
                        "photo" int   NOT NULL,
                        "name" varchar(50)   NOT NULL,
                        "description" varchar(50)   NOT NULL,
                        "published_date" timestamp   NOT NULL,
                        CONSTRAINT "pk_News" PRIMARY KEY (
                                                          "id"
                            )
);

CREATE TABLE "Stage" (
                         "id" int   NOT NULL,
                         "name" varchar(20)   NOT NULL,
                         "start_date" timestamp   NOT NULL,
                         "end_date" timestamp   NOT NULL,
                         "status" varchar   NOT NULL,
                         CONSTRAINT "pk_Stage" PRIMARY KEY (
                                                            "id"
                             )
);

CREATE TABLE "Competition" (
                               "id" bigint   NOT NULL,
                               "name" varchar(20)   NOT NULL,
                               "description" varchar(500)   NOT NULL,
                               "direction_id" int   NOT NULL,
                               "stage_id" int   NOT NULL,
                               CONSTRAINT "pk_Competition" PRIMARY KEY (
                                                                        "id"
                                   )
);

CREATE TABLE "Participants" (
                                "id" bigint   NOT NULL,
                                "user_id" int   NOT NULL,
                                "competition_id" int   NOT NULL,
                                "direction_id" int   NOT NULL,
                                "user_status" varchar   NOT NULL,
                                CONSTRAINT "pk_Participants" PRIMARY KEY (
                                                                          "id"
                                    )
);

ALTER TABLE "Region" ADD CONSTRAINT "fk_Region_country_id" FOREIGN KEY("country_id")
    REFERENCES "Country" ("id");

ALTER TABLE "Users" ADD CONSTRAINT "fk_Users_country_id" FOREIGN KEY("country_id")
    REFERENCES "Country" ("id");

ALTER TABLE "Users" ADD CONSTRAINT "fk_Users_region_id" FOREIGN KEY("region_id")
    REFERENCES "Region" ("id");

ALTER TABLE "Users" ADD CONSTRAINT "fk_Users_photo_id" FOREIGN KEY("photo_id")
    REFERENCES "Photo" ("id");

ALTER TABLE "Events" ADD CONSTRAINT "fk_Events_direction_id" FOREIGN KEY("direction_id")
    REFERENCES "Direction" ("id");

ALTER TABLE "Events" ADD CONSTRAINT "fk_Events_location" FOREIGN KEY("location")
    REFERENCES "Location" ("id");

ALTER TABLE "Partners" ADD CONSTRAINT "fk_Partners_icon_id" FOREIGN KEY("icon_id")
    REFERENCES "Photo" ("id");

ALTER TABLE "News" ADD CONSTRAINT "fk_News_photo" FOREIGN KEY("photo")
    REFERENCES "Photo" ("id");


ALTER TABLE "Competition" ADD CONSTRAINT "fk_Competition_direction_id" FOREIGN KEY("direction_id")
    REFERENCES "Direction" ("id");

ALTER TABLE "Competition" ADD CONSTRAINT "fk_Competition_stage_id" FOREIGN KEY("stage_id")
    REFERENCES "Stage" ("id");

ALTER TABLE "Participants" ADD CONSTRAINT "fk_Participants_user_id" FOREIGN KEY("user_id")
    REFERENCES "Users" ("id");

ALTER TABLE "Participants" ADD CONSTRAINT "fk_Participants_competition_id" FOREIGN KEY("competition_id")
    REFERENCES "Competition" ("id");

ALTER TABLE "Participants" ADD CONSTRAINT "fk_Participants_direction_id" FOREIGN KEY("direction_id")
    REFERENCES "Direction" ("id");