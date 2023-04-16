<script lang="ts">
  import type {Organisation} from "../../../types";
  import Card from "../../../compoents/Card/Card.svelte";

  export let org: Organisation;
</script>


<Card>
    <figure><img src="https://picsum.photos/seed/{org.id}/600/300" alt="image for the {org.name} organisation"/>
    </figure>
    <div class="card-body">
        <h2 class="card-title">{org.name}</h2>
        <p>{org.description || ''}</p>
        <div class="card-actions justify-end">
            <a class="btn btn-primary" href="/org/{org.id}">Open</a>
        </div>
    </div>
</Card>