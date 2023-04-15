<script>
    import CreateOrgModal from "./CreateOrgModal.svelte";
</script>

<div class="hero h-min w-screen">
    <div class="hero-content text-center">
        <div class="max-w-md">
            <h1 class="text-5xl font-bold">Welcome</h1>
            <p class="py-6">Start documenting your infrastructure now!</p>
            <label for="create-org-modal" class="btn btn-primary">Create an organisation</label>
        </div>
    </div>
</div>

<CreateOrgModal/>