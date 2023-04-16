<script lang="ts">
  import type {Infrastructure} from "../../../../types";
  import {currentOrganisation, loading} from "../../../../store";
  import {useQuery} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../utils/QueryUtils";
  import Card from "../../../../compoents/Card/Card.svelte";
  import {getInfrastructure, getOrganisation} from "../../../../services/axios";
  import CreateNodeModal from "./CreateNodeModal.svelte";
  import FloatingCtaModal from "../../../../compoents/FloatingCta/FloatingCtaModal.svelte";

  export let data;
  const {orgId} = data;
  let infrastructure: Infrastructure = {nodes: [], paths: []};
  $loading = true;

  useQuery(['find-organisation', orgId], () => getOrganisation(orgId))
    .subscribe(whenSuccessful(({data: {organisation, types, tags}}: any) => {
      $currentOrganisation = {
        ...organisation, types, tags
      };
    }));

  useQuery(['infrastructure', {orgId}], () => getInfrastructure(orgId))
    .subscribe(whenSuccessful(({data}) => {
      infrastructure = data as any;
      $loading = false;
    }));
</script>

<div class="p-2 flex flex-row gap-2 flex-wrap">
    {#each infrastructure.nodes as node}
        <Card>
            <pre class="p-4 break-all whitespace-pre-wrap">{JSON.stringify(node, null, 4)}</pre>
        </Card>
    {/each}
</div>

<FloatingCtaModal for="create-node-modal">
    <CreateNodeModal orgId="{orgId}"/>
</FloatingCtaModal>