import {writable} from 'svelte/store';
import type {Organisation, OrganisationTags, OrganisationTypes} from "./types";

type UserSession = null | {
  token?: string
}

export const userSession = writable<UserSession>({});

export const loading = writable(false);

export const currentOrganisation = writable<Organisation & OrganisationTypes & OrganisationTags | null>(null);
