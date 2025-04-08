CREATE TABLE pessoa(
    pes_id BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    pes_data_nascimento DATE,
    pes_mae VARCHAR(200) NOT NULL,
    pes_nome VARCHAR(200) NOT NULL,
    pes_pai VARCHAR(200),
    pes_sexo VARCHAR(10) NOT NULL,
    PRIMARY KEY(pes_id)
);
CREATE UNIQUE INDEX ukocylhib9rg64809e1o9li94so ON pessoa USING btree ("pes_nome");
