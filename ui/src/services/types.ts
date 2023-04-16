import type {Infrastructure, Organisation} from "../types";

// [GET] /org
export type getOrganisationsResponse = Organisation[];

// [POST] /org
export type createOrganisationRequest = {
  name: string;
  image?: string;
  description?: string;
}
export type createOrganisationResponse = Organisation;

// [GET] /org/{orgId}/node
export type getInfrastructureResponse =Infrastructure;