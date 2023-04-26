<script lang="ts">
  import {currentNode, currentOrganisation} from "../../../../store";
  import ChevronDoubleRightIcon from "../../../../compoents/Icon/ChevronDoubleRightIcon.svelte";

  function closePanel() {
      $currentNode = null;
    }
</script>

<button class="btn btn-sm btn-square right-3" on:click={closePanel}>
    <ChevronDoubleRightIcon/>
</button>

<div class="flex place-content-left items-center space-x-3 w-full p-4">
    <div class="avatar">
        <div class="w-10 h-10">
            <img src="{$currentOrganisation?.types?.find(t => t.name === $currentNode?.type)?.image}" alt="">
        </div>
    </div>
    <div>
        <div class="font-bold">{$currentNode?.name}</div>
        <div class="text-sm opacity-50">{$currentNode?.type}</div>
    </div>
</div>

<table class="border-collapse table-fixed w-full text-sm">
    <tbody class="">
    {#each Object.entries($currentNode?.tags ?? {}) as tag}
        {#if tag[1]}
            <tr>
                <td class="border-b border-slate-700 p-2 pl-8">{tag[0]}</td>
                <td class="border-b border-slate-700 p-2 pl-8">{tag[1]}</td>
            </tr>
        {/if}
    {/each}
    </tbody>
</table>