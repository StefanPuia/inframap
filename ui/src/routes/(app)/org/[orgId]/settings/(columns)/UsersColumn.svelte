<script>
    import {currentOrganisation} from "../../../../../../store";
    import SettingsColumn from "./SettingsColumn.svelte";
    import RemoveIcon from "../../../../../../compoents/Icon/RemoveIcon.svelte";
    import {useQueryClient} from "@sveltestack/svelte-query";
    import {onEnter} from "../../../../../../utils/KeyboardUtils";

    export let orgId = '';
    const queryClient = useQueryClient();
    const handleNewUser = async (user) => {
        alert("user: " + user);
        await queryClient.invalidateQueries('find-organisation');
    }
    const handleDeleteUser = async (user) => {
        // await deleteUser(orgId, user);
        await queryClient.invalidateQueries('find-organisation');
    }
</script>

<SettingsColumn title="Users" placeholder="Add User" onKeyPress="{onEnter(handleNewUser)}">
    {#each $currentOrganisation?.users ?? [] as user}
        <tr>
            <td class="w-full">{user}</td>
            <td class="text-center">
                <button class="btn btn-square btn-sm">
                    <RemoveIcon/>
                </button>
            </td>
        </tr>
    {/each}
</SettingsColumn>