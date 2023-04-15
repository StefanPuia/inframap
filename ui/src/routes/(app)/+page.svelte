<script lang="ts">
  import NoOrgsHero from "./org/NoOrgsHero.svelte";
  import type {Organisation} from "../../types.js";
  import OrgCard from "./org/OrgCard.svelte";

  let organisations: Organisation[] = [{
    id: 'uuid',
    name: 'My org'
  }];

  organisations = [];
</script>

{#each organisations as org}
    <OrgCard org="{org}"/>
{:else}
    <NoOrgsHero/>
{/each}