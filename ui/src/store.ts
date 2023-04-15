import { writable } from 'svelte/store';

type UserSession = null | {
  token?: string
}

export const userSession = writable<UserSession>({});
