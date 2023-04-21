<script lang="ts">
  import type {Infrastructure, InfrastructureNode} from "../../../../types";
  import {currentOrganisation, loading} from "../../../../store";
  import {useQuery, useQueryClient} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../utils/QueryUtils";
  import {getInfrastructure, getOrganisation} from "../../../../services/axios";
  import CreateNodeModal from "./CreateNodeModal.svelte";
  import FloatingCtaModal from "../../../../compoents/FloatingCta/FloatingCtaModal.svelte";
  import {ForceGraph} from "../../../../utils/ForceGraph";

  export let data;
  const {orgId} = data ?? {};
  const queryClient = useQueryClient();
  let infrastructure: Infrastructure = {nodes: [], paths: []};
  $loading = true;
  let el;

  useQuery(['find-organisation', orgId], () => getOrganisation(orgId))
    .subscribe(whenSuccessful(({data: {organisation, types, tags}}: any) => {
      $currentOrganisation = {
        ...organisation, types, tags
      };
      queryClient.invalidateQueries('infrastructure');
    }));

  useQuery(['infrastructure', {orgId}], () => getInfrastructure(orgId))
    .subscribe(whenSuccessful(({data}) => {
      infrastructure = data as any;
      const {nodes, paths} = infrastructure;

      if (el && $currentOrganisation) {
        while (el.firstChild) {
          el.removeChild(el.firstChild);
        }
        el.appendChild(ForceGraph<InfrastructureNode>({nodes, links: paths}, {
          types: ($currentOrganisation.types ?? []).reduce((acc, curr) => ({...acc, [curr.name]: curr.image}), {}),
        }))
      }

      $loading = false;
    }));
</script>

<!--<div class="p-2 flex flex-row gap-2 flex-wrap">-->
<!--    {#each infrastructure.nodes as node}-->
<!--        <Card>-->
<!--            <pre class="p-4 break-all whitespace-pre-wrap">{JSON.stringify(node, null, 4)}</pre>-->
<!--        </Card>-->
<!--    {/each}-->
<!--</div>-->

<FloatingCtaModal for="create-node-modal">
    <CreateNodeModal orgId="{orgId}"/>
</FloatingCtaModal>

<div bind:this={el} class="chart w-full relative" style="height: calc(100vh - 4rem - 1)"></div>