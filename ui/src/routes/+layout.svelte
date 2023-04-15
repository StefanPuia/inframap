<script lang="ts">
  import '../app.css';
  import {QueryClient, QueryClientProvider} from '@sveltestack/svelte-query';
  import {userSession} from '../store';
  import {goto} from '$app/navigation';
  import {onMount} from 'svelte'
  import {themeChange} from 'theme-change'

  const queryClient = new QueryClient()

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


<QueryClientProvider client={queryClient}>
    <div class="bg-base-200 min-h-screen min-w-screen">
        <slot/>
    </div>
</QueryClientProvider>
