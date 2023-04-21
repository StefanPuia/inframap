create (o:Organisation { id: 'c17ee878-d628-43e6-9935-800f0d668952', name: 'My org' });

MATCH (o:Organisation)
  WHERE o.id = 'c17ee878-d628-43e6-9935-800f0d668952'
CREATE(o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Router', image: 'https://code.benco.io/icon-collection/azure-icons/Virtual-Network-Gateways.svg'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Load Balancer', image: 'https://code.benco.io/icon-collection/azure-icons/Load-Balancers.svg'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'App Service', image: 'https://code.benco.io/icon-collection/azure-icons/App-Services.svg'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Virtual Machine', image: 'https://code.benco.io/icon-collection/azure-icons/Virtual-Machine.svg'}),
      (o)-[:HAS_TYPE]->(:InfrastructureType {name: 'Server', image: 'https://code.benco.io/icon-collection/azure-icons/App-Service-Plans.svg'});

MATCH (o:Organisation)
  WHERE o.id = 'c17ee878-d628-43e6-9935-800f0d668952'
CREATE(o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Country'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Zone'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Cloud Provider'}),
      (o)-[:HAS_TAG]->(:InfrastructureTag {name: 'Building'});

MATCH (o:Organisation)
  WHERE o.id = 'c17ee878-d628-43e6-9935-800f0d668952'
CREATE
  (o)<-[:BELONGS_TO]-(:Node {Building: 'AB1', name: 'uks-physical-1', Country: 'UK', id: '4c22a248-c5b1-45fd-9844-352d065e7b91',
          type:     'Server'}),
  (o)<-[:BELONGS_TO]-(:Node {Building: '', name: 'uks-vm-host-1', Country: 'UK', id: '7d518ee5-951b-41c7-929a-ecb3bddb38d2',
          type:     'Server'}),
  (o)<-[:BELONGS_TO]-(:Node {Building: '', `Cloud Provider`: 'on-prem', name: 'uks-vm-service-a', Country: 'UK',
          id:       'dab6d14e-484c-4eba-8249-1f7a158c59ce', type: 'Virtual Machine'}),
  (o)<-[:BELONGS_TO]-(:Node {Building: '', `Cloud Provider`: 'on-prem', name: 'uks-vm-service-b', Country: 'UK',
          id:       '8e614937-390a-480f-93a6-ff389fdb9bbe', type: 'Virtual Machine'});

MATCH (n:Node), (m:Node)
  WHERE n.id = '4c22a248-c5b1-45fd-9844-352d065e7b91' AND m.id = '7d518ee5-951b-41c7-929a-ecb3bddb38d2'
CREATE (n)-[:CONNECTED]->(m);

MATCH (n:Node), (m:Node)
  WHERE n.id = '7d518ee5-951b-41c7-929a-ecb3bddb38d2' AND m.id = 'dab6d14e-484c-4eba-8249-1f7a158c59ce'
CREATE (n)-[:CONNECTED]->(m);

MATCH (n:Node), (m:Node)
  WHERE n.id = '7d518ee5-951b-41c7-929a-ecb3bddb38d2' AND m.id = '8e614937-390a-480f-93a6-ff389fdb9bbe'
CREATE (n)-[:CONNECTED]->(m);

MATCH (n:Node), (m:Node)
  WHERE n.id = 'dab6d14e-484c-4eba-8249-1f7a158c59ce' AND m.id = '8e614937-390a-480f-93a6-ff389fdb9bbe'
CREATE (n)-[:CONNECTED]->(m);