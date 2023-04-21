import {dev} from '$app/environment';
import axios, {AxiosError} from 'axios';
import type {
  createNodeRequest,
  createNodeResponse,
  createOrganisationRequest,
  createOrganisationResponse, createTypeRequest,
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

const e = encodeURIComponent;
export const getOrganisations = () => service.get<getOrganisationsResponse>('/org');
export const getOrganisation = (orgId: string) => service.get<getOrganisationResponse>(`/org/${orgId}`);
export const createOrganisation = (data: createOrganisationRequest) => service.post<createOrganisationResponse>('/org', data);
export const getInfrastructure = (orgId: string) => service.get<getInfrastructureResponse>(`/org/${orgId}/node`);
export const createNode = (orgId: string, data: createNodeRequest) => service.post<createNodeResponse>(`/org/${orgId}/node`, data);
export const createType = (orgId: string, data: createTypeRequest) => service.post(`/org/${orgId}/type`, data);
export const deleteType = (orgId: string, type: string) => service.delete(`/org/${orgId}/type/${e(type)}`);
export const createTag = (orgId: string, tag: string) => service.post(`/org/${orgId}/tag`, {name: tag});
export const deleteTag = (orgId: string, tag: string) => service.delete(`/org/${orgId}/tag/${e(tag)}`);
