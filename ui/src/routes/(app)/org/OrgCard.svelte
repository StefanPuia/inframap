<script lang="ts">
    import type {Organisation} from "../../../types";

    export let org: Organisation;
</script>

<div class="container p-4">
    <div class="card w-96 bg-base-100 shadow-xl">
        <figure><img src="https://picsum.photos/seed/picsum/600/400" alt="Shoes"/></figure>
        <div class="card-body">
            <h2 class="card-title">{org.name}</h2>
            <p>{org.description || ''}</p>
            <div class="card-actions justify-end">
                <a class="btn btn-primary" href="/org/{org.id}">Open</a>
            </div>
        </div>
    </div>
</div>