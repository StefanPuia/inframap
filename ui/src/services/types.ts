import type {Infrastructure, InfrastructureNode, InfrastructureType, Organisation} from "../types";

// [GET] /org
export type getOrganisationsResponse = Organisation[];

// [POST] /org
export type createOrganisationRequest = {
  name: string;
  image?: string;
  description?: string;
}
export type createOrganisationResponse = Organisation;

// [GET] /org/{orgId}
export type getOrganisationResponse = {
  organisation: Organisation;
  types: InfrastructureType[];
  tags: string[];
};

// [GET] /org/{orgId}/node
export type getInfrastructureResponse = Infrastructure;

// [POST] /org/{orgId}/node
export type createNodeRequest = {
  name: string;
  type: string;
  tags: Record<string, any>;
}
export type createNodeResponse = InfrastructureNode;

// [POST] /org/{orgId}/type
export type createTypeRequest = {
  name: string;
  image: string;
}