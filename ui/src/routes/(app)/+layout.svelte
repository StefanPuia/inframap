<script>
    import Navbar from "../../compoents/Navbar/Navbar.svelte";
    import {loading} from "../../store";
    import {Stretch} from "svelte-loading-spinners";

    let isLoading = false;
    loading.subscribe(value => isLoading = value);
</script>

<Navbar/>

{#if isLoading}
    <div class="absolute left-0 top-0 w-screen h-screen z-50 grid place-items-center bg-neutral opacity-80">
        <Stretch size="60" color="hsl(var(--p))"/>
    </div>
{/if}
<slot/>