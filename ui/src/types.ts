export type Organisation = {
  id: string,
  name: string
  image?: string
  description?: string
}

export type OrganisationTypes = {
  types: string[]
}

export type OrganisationTags = {
  tags: string[]
}

export type InfrastructureNode = {
  id: string;
  name: string;
  type: string;
  tags: Record<string, any>
}

export type InfrastructureNodePath = {
  source: string;
  target: string;
}

export type Infrastructure = {
  nodes: InfrastructureNode[];
  paths: InfrastructureNodePath[];
}