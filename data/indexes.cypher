CREATE INDEX node_uuid IF NOT EXISTS FOR (n:Node) ON (n.uuid);

CREATE INDEX node_name IF NOT EXISTS FOR (n:Node) ON (n.name);

CREATE INDEX node_type IF NOT EXISTS FOR (n:Node) ON (n.type);

CREATE INDEX organisation_id IF NOT EXISTS FOR (o:Organisation) ON (o.id);

CREATE INDEX user_id IF NOT EXISTS FOR (u:User) ON (u.id);

CREATE INDEX user_name IF NOT EXISTS FOR (u:User) ON (u.username);

