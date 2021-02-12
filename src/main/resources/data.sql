INSERT INTO MATERIAL (nome) VALUES('tecido');
INSERT INTO MATERIAL (nome) VALUES('linha');
INSERT INTO MATERIAL (nome) VALUES('tinta branca');
INSERT INTO MATERIAL (nome) VALUES('tinta preta');
INSERT INTO MATERIAL (nome) VALUES('camisa');
INSERT INTO MATERIAL (nome) VALUES('calça');
INSERT INTO MATERIAL (nome) VALUES('bermuda');
INSERT INTO MATERIAL (nome) VALUES('saia');

INSERT INTO MATERIAIS_PRODUCAO (material_id, material_producao_id) VALUES(5, 1); -- 'camisa', 'tecido';
INSERT INTO MATERIAIS_PRODUCAO (material_id, material_producao_id) VALUES(5, 2); -- 'camisa', 'linha';

INSERT INTO MATERIAIS_PRODUCAO (material_id, material_producao_id) VALUES(6, 1); -- 'calça', 'tecido';

INSERT INTO ESTOQUE (quantidade) VALUES(5);
INSERT INTO ESTOQUE (quantidade) VALUES(50);
INSERT INTO ESTOQUE (quantidade) VALUES(3);
INSERT INTO ESTOQUE (quantidade) VALUES(10);
INSERT INTO ESTOQUE (quantidade) VALUES(0);
INSERT INTO ESTOQUE (quantidade) VALUES(0);
INSERT INTO ESTOQUE (quantidade) VALUES(0);
INSERT INTO ESTOQUE (quantidade) VALUES(0);

UPDATE MATERIAL SET estoque_id = 1 WHERE id = 1;
UPDATE MATERIAL SET estoque_id = 2 WHERE id = 2;
UPDATE MATERIAL SET estoque_id = 3 WHERE id = 3;
UPDATE MATERIAL SET estoque_id = 4 WHERE id = 4;
UPDATE MATERIAL SET estoque_id = 5 WHERE id = 5;
UPDATE MATERIAL SET estoque_id = 6 WHERE id = 6;
UPDATE MATERIAL SET estoque_id = 7 WHERE id = 7;
UPDATE MATERIAL SET estoque_id = 8 WHERE id = 8;

