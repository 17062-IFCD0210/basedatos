-- ---------------- --
-- consulta clasica --
-- ---------------- --
SELECT id,nombre,nota AS calificacion 
FROM test 
WHERE nota >= 5
ORDER BY nota DESC
LIMIT 20