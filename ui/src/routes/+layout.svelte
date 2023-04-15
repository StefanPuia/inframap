<script lang="ts">
  import '../app.css';
  import {userSession} from '../store';
  import {goto} from '$app/navigation';

  import {onMount} from 'svelte'
  import {themeChange} from 'theme-change'

  onMount(() => {
    themeChange(false)
  })

  userSession.subscribe((loggedIn) => {
    if (!loggedIn) {
      goto('/login');
    }
  });
</script>

<svelte:head>
    <title>InfraMap</title>
</svelte:head>

<div class="bg-base-200 h-screen w-screen">
    <slot/>
</div>