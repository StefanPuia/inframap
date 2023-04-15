import { writable } from 'svelte/store';
import type {Organisation} from "./types";

type UserSession = null | {
  token?: string
}

export const userSession = writable<UserSession>({});

export const loading = writable(false);

export const organisations = writable<Organisation[]>([]);