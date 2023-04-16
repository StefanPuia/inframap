import {dev} from '$app/environment';
import axios, {AxiosError} from 'axios';
import type {
  createNodeRequest,
  createNodeResponse,
  createOrganisationRequest,
  createOrganisationResponse,
  getInfrastructureResponse,
  getOrganisationResponse,
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

service.interceptors.response.use((response: any) => response,
  (error: AxiosError) => {
    const responseData = error.response;
    console.error(responseData);
    if ((responseData?.data as any)?._embedded?.errors) {
      return Promise.reject((responseData?.data as any)?._embedded?.errors.reduce((errors: string[], err: any) => [...errors, err.message], []));
    }
    return Promise.reject(responseData?.data);
  });

export const getOrganisations = () => service.get<getOrganisationsResponse>('/org');
export const getOrganisation = (orgId: string) => service.get<getOrganisationResponse>(`/org/${orgId}`);
export const createOrganisation = (data: createOrganisationRequest) => service.post<createOrganisationResponse>('/org', data);
export const getInfrastructure = (orgId: string) => service.get<getInfrastructureResponse>(`/org/${orgId}/node`);
export const createNode = (orgId: string, data: createNodeRequest) => service.post<createNodeResponse>(`/org/${orgId}/node`, data);
