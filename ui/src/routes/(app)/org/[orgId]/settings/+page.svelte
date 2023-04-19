<script lang="ts">
  import {currentOrganisation, loading} from "../../../../../store";
  import {useQuery, useQueryClient} from "@sveltestack/svelte-query";
  import {whenSuccessful} from "../../../../../utils/QueryUtils";
  import {createTag, createType, deleteTag, deleteType, getOrganisation} from "../../../../../services/axios";
  import type {Infrastructure} from "../../../../../types";
  import RemoveIcon from "../../../../../compoents/Icon/RemoveIcon.svelte";
  import SettingsColumn from "./SettingsColumn.svelte";

  export let data;
  const {orgId} = data ?? {};
  const queryClient = useQueryClient();
  let infrastructure: Infrastructure = {nodes: [], paths: []};
  $loading = true;
  let el;

  const onEnter = (handle: (value: string) => Promise<void>) => {
    return (e) => {
      if (e.code === 'Enter') {
        handle(e.target.value)
          .then(async () => {
            e.target.value = '';
            await queryClient.invalidateQueries('find-organisation');
          })
          .catch(console.error);
      }
    }
  }

  const handleDeleteType = async (type) => {
    await deleteType(orgId, type);
    await queryClient.invalidateQueries('find-organisation');
  }

  const handleDeleteTag = async (tag) => {
    await deleteTag(orgId, tag);
    await queryClient.invalidateQueries('find-organisation');
  }

  const handleDeleteUser = async (user) => {
    // await deleteUser(orgId, user);
    await queryClient.invalidateQueries('find-organisation');
  }

  const handleNewType = async (type) => {
    await createType(orgId, type);
  }
  const handleNewTag = async (tag) => {
    await createTag(orgId, tag);
  }
  const handleNewUser = async (user) => {
    alert("user: " + user)
  }

  useQuery(['find-organisation', orgId], () => getOrganisation(orgId))
    .subscribe(whenSuccessful(({data: {organisation, types, tags}}: any) => {
      $currentOrganisation = {
        ...organisation, types, tags
      };
      $loading = false;
    }));
</script>

<div class="flex flex-row p-4 flex-wrap">
    <SettingsColumn title="Types" placeholder="Add Type" onKeyPress="{onEnter(handleNewType)}">
        {#each $currentOrganisation?.types ?? [] as type}
            <tr>
                <td class="w-full">
                    <div class="flex items-center space-x-3">
                        <div class="avatar">
                            <div class="mask mask-squircle w-12 h-12">
                                <img src="https://www.bigbiz.com/bigbiz/icons/ultimate/Comic/Comic{Math.floor(Math.random() * 64 + 1)}.gif"
                                     alt="Avatar Tailwind CSS Component"/>
                            </div>
                        </div>
                        <div>
                            <div class="break-all whitespace-pre-wrap">{type}</div>
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
    <SettingsColumn title="Tags" placeholder="Add Tag" onKeyPress="{onEnter(handleNewTag)}">
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
</div>

