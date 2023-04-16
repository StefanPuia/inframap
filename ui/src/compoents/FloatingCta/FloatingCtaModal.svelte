<script>
    import PlusIcon from "../Icon/PlusIcon.svelte";

    let forId;
    export { forId as for };
</script>

<div class="fixed bottom-3 right-3">
    <label for="{forId}" class="btn btn-circle">
        <PlusIcon />
    </label>
    <slot/>
</div>