<script lang="ts">
  import type {Infrastructure} from "../../../../types";
  import {loading} from "../../../../store";
  import {useQuery} from "@sveltestack/svelte-query";
  import Api from "../../../../services/Api";
  import {whenSuccessful} from "../../../../utils/QueryUtils";

  export let data;
  const {orgId} = data;

  let infrastructure: Infrastructure = {nodes: [], paths: []};
  $loading = true;

  useQuery<Infrastructure>(['infrastructure', {orgId}], () => Api.get(`org/${orgId}/node`, {}))
    .subscribe(whenSuccessful((data) => {
      infrastructure = data as any;
      $loading = false;
    }));
</script>


<ul>
    {#each infrastructure.nodes as node}
        <li>{JSON.stringify(node)}</li>
    {/each}
</ul>