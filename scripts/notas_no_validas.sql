-- INSERT INTO `test`
-- (`nombre`, `nota`, `telefono`, `fecha`)
-- VALUES
-- ('Mario', -4.50, NULL, '2015-09-02 9:00:00')

-- buscar registros con 'nota' no validas
-- la 'nota' tiene que tener un valor entre 0.00 y 10.00
-- select * from test
-- where nota > 10 || nota < 0;

-- eliminar los registros no validos
delete from test
where nota > 10 or nota < 0;

select * from test;
