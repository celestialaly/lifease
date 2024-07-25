<script setup lang="ts">
import RoomWithTask from "./components/RoomWithTask.vue";

const config = useRuntimeConfig()
const { data } = await useFetch(config.public.backendApiUrl + '/room')
</script>

<template>
  <div class="bg-slate-100 h-screen">
    <NuxtRouteAnnouncer />
    <NavBar/>

    <div class="container mx-auto mt-5">
      <div class="w-full columns-2 md:columns-3 gap-4">
        <div v-for="room in data" class="w-full break-inside-avoid">
          <RoomWithTask :room="room"></RoomWithTask>
        </div>
      </div>
    </div>
  </div>
</template>
