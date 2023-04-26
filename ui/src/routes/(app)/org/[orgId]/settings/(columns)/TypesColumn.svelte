<script lang="ts">
  import {currentOrganisation} from "../../../../../../store";
  import SettingsColumn from "./SettingsColumn.svelte";
  import RemoveIcon from "../../../../../../compoents/Icon/RemoveIcon.svelte";
  import {createType, deleteType} from "../../../../../../services/axios";
  import {useQueryClient} from "@sveltestack/svelte-query";
  import {onEnter} from "../../../../../../utils/KeyboardUtils";
  import type {InfrastructureType} from "../../../../../../types";

  export let orgId = '';
  const queryClient = useQueryClient();

  let imageInput = '';
  const handleNewType = async (type: string) => {
    await createType(orgId, {name: type, image: imageInput});
    await queryClient.invalidateQueries('find-organisation');
  }
  const handleDeleteType = async (type: InfrastructureType) => {
    await deleteType(orgId, type.name);
    await queryClient.invalidateQueries('find-organisation');
  }
</script>

<SettingsColumn title="Types" placeholder="Add Type">
    <tr>
        <td colspan="2">
            <h3 class="text-lg pb-2">
                Create new type
                <a class="link-accent float-right" href="https://code.benco.io/icon-collection/azure-icons/"
                   target="_blank">icons</a>
            </h3>
            <div class="flex flex-wrap flex-col gap-2">
                <input type="text" placeholder="Image URL" class="input input-bordered w-full" bind:value={imageInput}/>
                <input type="text" placeholder="Name" class="input input-bordered w-full"
                       on:keypress={onEnter(handleNewType)}/>
            </div>
        </td>
    </tr>
    {#each $currentOrganisation?.types ?? [] as type}
        <tr>
            <td class="w-full">
                <div class="flex items-center space-x-3">
                    <div class="avatar">
                        <div class="w-8 h-8">
                            <img src="{type.image}" alt="Avatar Tailwind CSS Component"/>
                        </div>
                    </div>
                    <div>
                        <div class="break-all whitespace-pre-wrap">{type.name}</div>
                    </div>
                </div>
            </td>
            <td class="text-center">
                <button class="btn btn-square btn-sm" on:click={handleDeleteType(type)}>
                    <RemoveIcon/>
                </button>
            </td>
        </tr>
    {/each}
</SettingsColumn>