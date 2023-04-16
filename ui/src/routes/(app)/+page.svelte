<script lang="ts">
  import NoOrgsHero from "./org/NoOrgsHero.svelte";
  import type {Organisation} from "../../types.js";
  import OrgCard from "./org/OrgCard.svelte";
  import Api from "../../services/Api";
  import {loading} from "../../store";
  import CreateOrgCard from "./org/CreateOrgCard.svelte";
  import {useQuery} from '@sveltestack/svelte-query';
  import {whenSuccessful} from "../../utils/QueryUtils";

  let organisations: Organisation[] = [];
  $loading = true;

  useQuery<Organisation[]>('list-organisations', () => Api.get("org", {}))
    .subscribe(whenSuccessful(data => {
      organisations = data as any;
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
