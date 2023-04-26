<script lang="ts">
  import type {Infrastructure, InfrastructureNode} from "../../../../types";
  import {currentOrganisation, loading, currentNode} from "../../../../store";
  import {useQuery, useQueryClient} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../utils/QueryUtils";
  import {getInfrastructure, getOrganisation} from "../../../../services/axios";
  import CreateNodeModal from "./CreateNodeModal.svelte";
  import FloatingCtaModal from "../../../../compoents/FloatingCta/FloatingCtaModal.svelte";
  import {ForceGraph} from "../../../../utils/ForceGraph";
  import {handleSvgClick} from "../../../../utils/SvgUtils";
  import NodeInfoPanel from "./NodeInfoPanel.svelte";

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

  const clickHandler = handleSvgClick('g', (el) => {
    let node = infrastructure.nodes.find(d => d.id === el.dataset.id);
    $currentNode = node;
      console.log(node)
  });

  useQuery(['infrastructure', {orgId}], () => getInfrastructure(orgId))
    .subscribe(whenSuccessful(({data}) => {
      infrastructure = data as any;
      const {nodes, paths} = infrastructure;

      if (el && $currentOrganisation) {
        el.removeEventListener('click', clickHandler);
        while (el.lastChild && el.lastChild.nodeName === 'svg') {
          el.removeChild(el.lastChild);
        }
        el.appendChild(ForceGraph<InfrastructureNode>({nodes, links: paths}, {
          types: ($currentOrganisation.types ?? []).reduce((acc, curr) => ({...acc, [curr.name]: curr.image}), {}),
        }))
        el.addEventListener('click', clickHandler);
      }

      $loading = false;
    }));
</script>

<div style="height: calc(100vh - 4rem - 2px)" class="w-full relative flex flex-row">
    <div bind:this={el} class="chart {$currentNode ? 'w-3/4' : 'w-full'} relative h-full">
        <FloatingCtaModal for="create-node-modal" >
            <CreateNodeModal orgId="{orgId}"/>
        </FloatingCtaModal>
    </div>
    <div class="{$currentNode ? 'w-1/4' : 'hidden'} h-full relative border-l-accent z-50 p-2 bg-base-100/30">
        <NodeInfoPanel/>
    </div>
</div>

