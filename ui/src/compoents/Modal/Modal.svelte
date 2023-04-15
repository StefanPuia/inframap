<script lang="ts">
  export let id: string;
</script>

<input type="checkbox" id="{id}" class="modal-toggle"/>
<label for="{id}" class="modal cursor-pointer">
    <label class="modal-box relative" for="">
        <slot/>
    </label>
</label>