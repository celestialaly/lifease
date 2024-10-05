<script setup lang="ts">
const dayjs = useDayjs()
const config = useRuntimeConfig()
const { data } = await useFetch(config.public.backendApiUrl + '/dailyTask')

async function toggleTaskCompletion(task) {
    await $fetch(config.public.backendApiUrl + `/dailyTask/${task.dailyTaskId}/toggle`, {method: 'POST'})
    task.completed.push(dayjs().format('YYYY-MM-DD'))
}
</script>

<template>
    <div class="w-100% flex flex-row items-start justify-center">
        <template v-for="(task, index) in data">
            <div class="w-auto text-center items-center">
                <button class="inline-flex items-center justify-center w-10 h-10 rounded-full transition-colors duration-150 focus:shadow-outline
                hover:bg-pink-800
                border border-pink-600"
                :class="{ 'bg-pink-800': task.completed.includes($dayjs().format('YYYY-MM-DD')) }">
                    <UIcon :name="`noto:${task.icon}`" class="w-5 h-5" @click="toggleTaskCompletion(task)" />
                </button><br/>
                <span class="text-slate-200/60 text-xs">{{ task.name }}</span>
            </div>
            <span v-if="index < data.length - 1" class="p-0 m-0 h-5 grow border-b border-pink-600"></span>
        </template>
    </div>
</template>