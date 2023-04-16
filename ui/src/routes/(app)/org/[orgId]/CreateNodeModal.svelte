<script>
    import Modal from "../../../../compoents/Modal/Modal.svelte";
    import {useQueryClient} from "@sveltestack/svelte-query";
    import {currentOrganisation, loading} from "../../../../store";
    import {createNode} from "../../../../services/axios";
    import ErrorAlert from "../../../../compoents/Alert/ErrorAlert.svelte";

    export let orgId;

    let errors;
    let formInputName = "";
    let formInputType = "";
    let formInputTags = {}

    const queryClient = useQueryClient();
    const handleSubmit = async () => {
        try {
            $loading = true;
            await createNode(orgId, {name: formInputName, type: formInputType, tags: formInputTags});
            document.querySelector("#create-node-modal").checked = false;
            await queryClient.invalidateQueries('infrastructure');
        } catch (e) {
            errors = e;
            $loading = false;
        }
    }
</script>

<Modal id="create-node-modal">
    <h3 class="text-lg font-bold">Create service</h3>

    <form method="post" class="flex flex-col justify-center items-center gap-2 my-2"
          on:submit|preventDefault={handleSubmit}>
        <div class="form-control w-full">
            <input type="text" placeholder="Name" class="input input-bordered w-full"
                   bind:value={formInputName}/>
        </div>
        <div class="form-control w-full">
            <select class="select w-full input-bordered" bind:value={formInputType}>
                <option disabled selected value="">Type</option>
                {#each $currentOrganisation?.types ?? [] as type}
                    <option>{type}</option>
                {/each}
            </select>
        </div>
        {#each $currentOrganisation?.tags ?? [] as tag}
            <div class="form-control w-full">
                <input type="text" placeholder="{tag}" class="input input-bordered w-full"
                       bind:value={formInputTags[tag]}/>
            </div>
        {/each}

        {#if errors}
            <ErrorAlert>{#each errors as error}{error}<br/>{/each}</ErrorAlert>
        {/if}
        <button type="submit" class="btn btn-primary btn-wide">Create</button>
    </form>
</Modal>