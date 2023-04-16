<script lang="ts">
  import {loading} from "../../../store";
  import {useQueryClient} from "@sveltestack/svelte-query";
  import Modal from "../../../compoents/Modal/Modal.svelte";
  import {createOrganisation} from "../../../services/axios";

  let formInputName;
  let formInputImage;
  let formInputDescription;

  const queryClient = useQueryClient();
  const handleSubmit = async () => {
    try {
      loading.set(true);
      await createOrganisation({name: formInputName, image: formInputImage, description: formInputDescription});
      await queryClient.invalidateQueries('list-organisations');
      document.querySelector("#create-org-modal").checked = false;
    } catch (e) {
      alert(e);
    }
  }
</script>

<Modal id="create-org-modal">
    <h3 class="text-lg font-bold">Create organisation</h3>
    <form method="post" class="flex flex-col justify-center items-center"
          on:submit|preventDefault={handleSubmit}>
        <div class="form-control w-full m-2">
            <input name="name" type="text" placeholder="Name" class="input input-bordered w-full"
                   bind:value={formInputName}/>
        </div>
        <div class="form-control w-full m-2">
            <input type="text" placeholder="Image" class="input input-bordered w-full" bind:value={formInputImage}/>
        </div>
        <div class="form-control w-full m-2">
            <input type="text" placeholder="Description" class="input input-bordered w-full"
                   bind:value={formInputDescription}/>
        </div>
        <button type="submit" class="btn btn-primary btn-wide">Create</button>
    </form>
</Modal>