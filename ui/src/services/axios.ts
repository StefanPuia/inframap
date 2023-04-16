import {dev} from '$app/environment';
import axios from 'axios';
import type {
  createOrganisationRequest,
  createOrganisationResponse,
  getInfrastructureResponse,
  getOrganisationsResponse
} from "./types";

export const baseURL = dev
  ? 'http://localhost:8080'
  : '/api';

const service = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

export const getOrganisations = () => service.get<getOrganisationsResponse>('/org');
export const createOrganisation = (data: createOrganisationRequest) => service.post<createOrganisationResponse>('/org', data);
export const getInfrastructure = (orgId: string) => service.get<getInfrastructureResponse>(`/org/${orgId}/node`);
