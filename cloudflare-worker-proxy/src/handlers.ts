import { HandlerParams } from "./types";

const cloneRequest = (url: URL, request: Request) => {
  return new Request(url, {
    method: request.method,
    headers: request.headers,
    body: request.body,
    cf: request.cf,
    fetcher: request.fetcher,
    redirect: request.redirect,
    signal: request.signal,
  });
};

export const handleApi = async ({
  url,
  request,
  env,
}: HandlerParams): Promise<Response> => {
  const redirect = new URL(
    url.toString().replace(new RegExp(`^${url.origin}/api`), env.API_URL)
  );
  return fetch(cloneRequest(redirect, request));
};

export const handleFrontend = async ({
  url,
  request,
  env,
}: HandlerParams): Promise<Response> => {
  const redirect = url
    .toString()
    .replace(new RegExp(`^${url.origin}`), env.FRONTEND_URL);
  return await fetch(cloneRequest(new URL(redirect), request));
};
