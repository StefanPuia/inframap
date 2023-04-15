<script lang="ts">
  const handleSubmit = e => {
    // getting the action url
    const ACTION_URL = e.target.action

    // get the form fields data and convert it to URLSearchParams
    const formData = new FormData(e.target)
    const data = new URLSearchParams()
    for (let field of formData) {
      const [key, value] = field
      data.append(key, value as any);
    }

    // check the form's method and send the fetch accordingly
    if (e.target.method.toLowerCase() == 'get') fetch(`${ACTION_URL}?${data}`)
    else {
      fetch(ACTION_URL, {
        method: 'POST',
        body: data
      })
    }
  }
</script>

<input type="checkbox" id="my-modal-4" class="modal-toggle"/>
<label for="my-modal-4" class="modal cursor-pointer">
    <label class="modal-box relative" for="">
        <h3 class="text-lg font-bold">Create organisation</h3>
        <form action="/api/org" method="post" class="flex flex-col justify-center items-center"
              on:submit|preventDefault={handleSubmit}>
            <div class="form-control w-full m-2">
                <input type="text" placeholder="Name" class="input input-bordered w-full"/>
            </div>
            <div class="form-control w-full m-2">
                <input type="text" placeholder="Image" class="input input-bordered w-full"/>
            </div>
            <div class="form-control w-full m-2">
                <input type="text" placeholder="Description" class="input input-bordered w-full"/>
            </div>
            <button class="btn btn-primary btn-wide" type="submit">Create</button>
        </form>
    </label>
</label>