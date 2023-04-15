<script>
    import CreateOrgModal from "./CreateOrgModal.svelte";
    import Card from "../../../compoents/Card/Card.svelte";
</script>

<Card>
    <div class="card-body">
        <h2 class="card-title">New organisation</h2>
        <p></p>
        <div class="card-actions justify-end">
            <label for="create-org-modal" class="btn btn-primary">Create an organisation</label>
        </div>
    </div>
</Card>

<CreateOrgModal/>