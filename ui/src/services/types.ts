import type {Infrastructure, InfrastructureNode, Organisation} from "../types";

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
  types: string[];
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
