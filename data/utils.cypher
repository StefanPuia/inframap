// match everything
MATCH (n)
RETURN n
  LIMIT 25
;


// delete everything
MATCH (n)
OPTIONAL MATCH (n)-[r]-()
DELETE n, r;


// delete only nodes
MATCH (n:Node)
OPTIONAL MATCH (n)-[r]-()
DELETE n, r;
