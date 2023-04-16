export type Organisation = {
  id: string,
  name: string
  image?: string
  description?: string
}

export type InfrastructureNode = {
  id: string;
  name: string;
  type: string;
  tags: Record<string, any>
}

export type InfrastructureNodePath = {
  from: string;
  to: string;
}

export type Infrastructure = {
  nodes: InfrastructureNode[];
  paths: InfrastructureNodePath[];
}