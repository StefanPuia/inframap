<script>
    import ErrorIcon from "../Icon/ErrorIcon.svelte";
</script>

<div class="alert alert-error shadow-lg">
    <div>
        <ErrorIcon/>
        <span><slot/></span>
    </div>
</div>