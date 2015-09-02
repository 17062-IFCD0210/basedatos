-- INSERT INTO `test` 
-- (`nombre`, `nota`, `telefono`, `fecha`)
-- VALUES
-- ('Alain', -3.5, NULL, '2015-08-19 08:40:00')

-- Buscar registros con 'nota' no validas
-- la nota tiene que tener un valor entre 0.0 y 10.0
select * from test
where nota < 0.0 or nota > 10.0;

-- Eliminar los registros invalidos

DELETE FROM `test` 
WHERE nota < 0.0 or nota > 10.0;

select * from test;
