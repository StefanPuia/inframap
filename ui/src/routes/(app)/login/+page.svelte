<script lang="ts">
  import {userSession} from '../../../store';
  import {goto} from '$app/navigation';

  userSession.subscribe((value) => {
    if (value) {
      goto('/');
    }
  });
</script>

<div class="hero min-h-screen bg-base-200">
    <div class="hero-content flex-col lg:flex-row-reverse">
        <div class="card lg:card-side bg-base-100 shadow-xl">
            <figure><img src="/login-hero.jpg" alt="Album"/></figure>
            <div class="card-body">
                <h2 class="card-title">InfraMap</h2>
                <p>
                    Document, visualise and understand your whole infrastructure.<br/>From edge solutions to
                    bare metal servers.
                </p>
                <div class="card-actions justify-end">
                    <button class="btn gap-2" on:click={() => userSession.set({ token: '' })}>
                        <img src="/icons/google.svg" alt="google icon"/>
                        Sign in with Google
                    </button>
                    <button class="btn gap-2">
                        <img src="/icons/microsoft.svg" alt="microsoft icon"/>
                        Sign in with Microsoft
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
