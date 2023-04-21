<script>
    export let title = '';
    export let placeholder = '';
    export let colspan = 2;
    export let onKeyPress = () => {
    }
</script>

<div class="p-4 xl:w-1/3 md:w-1/2 w-full">
    <h2 class="text-2xl font-bold mb-4">{title}</h2>
    <table class="table w-full static">
        <tbody>
        <slot/>
        </tbody>
    </table>
</div>