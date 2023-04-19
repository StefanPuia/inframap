import type {PageLoad} from "./$types";

export const load: PageLoad = ({params}: any) => {
  return {
    orgId: params.orgId
  };
}