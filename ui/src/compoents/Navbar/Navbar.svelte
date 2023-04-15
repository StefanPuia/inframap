<script lang="ts">
  import {userSession} from "../../store";

  function logout() {
    userSession.set(null);
  }
</script>

<div class="navbar bg-base-100 drop-shadow-sm">
    <div class="flex-1">
        <a class="btn btn-ghost normal-case text-xl" href="/">InfraMap</a>
    </div>
    <div class="flex-none">
        <div class="dropdown dropdown-end">
            <label tabindex="0" class="btn btn-ghost btn-circle avatar">
                <div class="w-10 rounded-full">
                    <img src="/favicon.png"/>
                </div>
            </label>
            <ul
                    tabindex="0"
                    class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52"
            >
                <li>
                    <a class="justify-between">
                        Profile
                        <span class="badge">New</span>
                    </a>
                </li>
                <li><a>Settings</a></li>
                <li><a on:click={logout}>Logout</a></li>
            </ul>
        </div>
    </div>
</div>