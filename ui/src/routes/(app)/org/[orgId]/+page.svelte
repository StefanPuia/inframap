<script lang="ts">
  import type {Infrastructure} from "../../../../types";
  import {loading} from "../../../../store";
  import {useQuery} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../utils/QueryUtils";
  import Card from "../../../../compoents/Card/Card.svelte";
  import FloatingCta from "../../../../compoents/FloatingCta/FloatingCta.svelte";
  import {getInfrastructure} from "../../../../services/axios";

  export let data;
  const {orgId} = data;

  let infrastructure: Infrastructure = {nodes: [], paths: []};
  $loading = true;

  useQuery(['infrastructure', {orgId}], () => getInfrastructure(orgId))
    .subscribe(whenSuccessful(({data}) => {
      infrastructure = data as any;
      $loading = false;
    }));
</script>


<div class="p-2 flex flex-row gap-2">
    {#each infrastructure.nodes as node}
        <Card>
            <pre class="p-4 break-all whitespace-pre-wrap">{JSON.stringify(node, null, 4)}</pre>
        </Card>
    {/each}
</div>

<FloatingCta/>