<script>
    import {currentOrganisation} from "../../../../../../store";
    import SettingsColumn from "./SettingsColumn.svelte";
    import RemoveIcon from "../../../../../../compoents/Icon/RemoveIcon.svelte";
    import {createTag, deleteTag} from "../../../../../../services/axios";
    import {useQueryClient} from "@sveltestack/svelte-query";
    import {onEnter} from "../../../../../../utils/KeyboardUtils";

    export let orgId = '';
    const queryClient = useQueryClient();
    const handleNewTag = async (tag) => {
        await createTag(orgId, tag);
        await queryClient.invalidateQueries('find-organisation');
    }
    const handleDeleteTag = async (tag) => {
        await deleteTag(orgId, tag);
        await queryClient.invalidateQueries('find-organisation');
    }
</script>

<SettingsColumn title="Tags" placeholder="Add Tag" onKeyPress="{onEnter(handleNewTag)}">
    <tr>
        <td colspan="2">
            <h3 class="text-lg pb-2">Create new tag</h3>
            <input type="text" placeholder="Name" class="input input-bordered w-full"
                   on:keypress={onEnter(handleNewTag)}/>
        </td>
    </tr>
    {#each $currentOrganisation?.tags ?? [] as tag}
        <tr>
            <td class="w-full">{tag}</td>
            <td class="text-center">
                <button class="btn btn-square btn-sm" on:click={handleDeleteTag(tag)}>
                    <RemoveIcon/>
                </button>
            </td>
        </tr>
    {/each}
</SettingsColumn>