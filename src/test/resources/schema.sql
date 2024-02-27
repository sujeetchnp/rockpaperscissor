
DROP TABLE IF EXISTS game_round;

DROP TABLE IF EXISTS game;

DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    playername character varying(255) DEFAULT NOT NULL,
    city character varying(255),
    country character varying(255),
    email character varying(255),
    last_logged_in_date timestamp without time zone,
    password character varying(255),
    registered_date timestamp without time zone NOT NULL,
    user_role character varying(255),
    CONSTRAINT player_pkey PRIMARY KEY (playername)
);

CREATE TABLE game
(
    game_id integer NOT NULL AUTO_INCREMENT,
    game_date timestamp without time zone,
    game_result character varying(255) ,
    max_rounds integer,
    playername character varying(255),
    CONSTRAINT game_pkey PRIMARY KEY (game_id),
    CONSTRAINT fkreuatw7ouun2n3i63qlvah3ph FOREIGN KEY (playername)
    REFERENCES player (playername)

    );


CREATE TABLE game_round
(
    id integer NOT NULL AUTO_INCREMENT,
    computer_choice character varying(255),
    player_choice character varying(255) ,
    round_number integer,
    round_result character varying(255),
    game_id integer,
    CONSTRAINT game_round_pkey PRIMARY KEY (id),
    CONSTRAINT fkoy982xqm8lmwtwackq4y4jct1 FOREIGN KEY (game_id)
    REFERENCES game (game_id)

    );