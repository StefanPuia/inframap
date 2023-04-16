<script lang="ts">
  import '../app.css';
  import {QueryClient, QueryClientProvider} from '@sveltestack/svelte-query';
  import {currentOrganisation, userSession} from '../store';
  import {goto} from '$app/navigation';
  import {onMount} from 'svelte'
  import {themeChange} from 'theme-change'

  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        refetchOnWindowFocus: false,
        refetchOnReconnect: false,
      }
    }
  })

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
    {#if $currentOrganisation}<title>InfraMap - {$currentOrganisation.name}</title>{:else}<title>InfraMap</title>{/if}
</svelte:head>


<QueryClientProvider client={queryClient}>
    <div class="bg-base-200 min-h-screen min-w-screen">
        <slot/>
    </div>
</QueryClientProvider>
