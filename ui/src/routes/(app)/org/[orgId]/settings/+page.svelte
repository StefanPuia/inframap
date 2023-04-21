<script lang="ts">
  import {currentOrganisation, loading} from "../../../../../store";
  import {useQuery} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../../utils/QueryUtils";
  import {getOrganisation} from "../../../../../services/axios";
  import TypesColumn from "./(columns)/TypesColumn.svelte";
  import TagsColumn from "./(columns)/TagsColumn.svelte";
  import UsersColumn from "./(columns)/UsersColumn.svelte";

  export let data;
  const {orgId} = data ?? {};

  $loading = true;
  useQuery(['find-organisation', orgId], () => getOrganisation(orgId))
    .subscribe(whenSuccessful(({data: {organisation, types, tags}}: any) => {
      $currentOrganisation = {
        ...organisation, types, tags
      };
      $loading = false;
    }));
</script>

<div class="flex flex-row p-4 flex-wrap">
    <TypesColumn orgId="{orgId}"/>
    <TagsColumn orgId="{orgId}"/>
    <UsersColumn orgId="{orgId}"/>
</div>

