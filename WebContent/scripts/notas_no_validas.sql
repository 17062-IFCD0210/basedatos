-- INSERT INTO `test` 
-- (`nombre`, `nota`, `telefono`, `fecha`) 
-- VALUES
-- ('mario', 4.5, NULL, '2015-09-02 09:00:00')

-- select * from test

-- BUSCAR REGISTRO CON NOTA NO VALIDAS, LA NOTA TIENE QUE TENER UN VALOR ENTRE 0.0 Y 10.0

SELECT *
FROM `test`
WHERE `nota` <0.0 OR `nota` >10.0;

-- ELIMINAR LOS REGISTROS NO VALIDOS

DELETE FROM `test` 
WHERE `nota` <0.0 OR `nota` >10.0;

SELECT * FROM TEST;




