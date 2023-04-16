create (o:Organisation { id: 'c17ee878-d628-43e6-9935-800f0d668952', name: 'My org' });

MATCH (o:Organisation)
  WHERE o.id = 'c17ee878-d628-43e6-9935-800f0d668952'
CREATE(o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Router'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Load Balancer'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'App Service'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Virtual Machine'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Server'});

MATCH (o:Organisation)
  WHERE o.id = 'c17ee878-d628-43e6-9935-800f0d668952'
CREATE(o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Country'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Zone'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Cloud Provider'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Building'});