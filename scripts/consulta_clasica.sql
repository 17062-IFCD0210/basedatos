select id, nombre, nota as calificacion
from test
where nota < 5
order by nota desc