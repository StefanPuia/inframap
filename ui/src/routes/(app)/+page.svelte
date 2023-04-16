<script lang="ts">
  import NoOrgsHero from "./org/NoOrgsHero.svelte";
  import type {Organisation} from "../../types.js";
  import OrgCard from "./org/OrgCard.svelte";
  import {loading} from "../../store";
  import CreateOrgCard from "./org/CreateOrgCard.svelte";
  import {useQuery} from '@sveltestack/svelte-query';
  import {whenSuccessful} from "../../utils/QueryUtils";
  import {getOrganisations} from "../../services/axios";

  let organisations: Organisation[] = [];
  $loading = true;

  useQuery('list-organisations', () => getOrganisations())
    .subscribe(whenSuccessful(({data}) => {
      organisations = data;
      $loading = false
    }));
</script>

{#if organisations?.length}
    <div class="p-4 flex flex-row flex-wrap gap-4">
        <CreateOrgCard/>
        {#each organisations as org}
            <OrgCard org="{org}"/>
        {/each}
    </div>
{:else}
    <NoOrgsHero/>
{/if}
