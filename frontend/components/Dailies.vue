<script setup lang="ts">
const tasks = [
    { name: 'Pills', icon: 'noto:pill' },
    { name: 'Lumino', icon: 'noto:light-bulb' },
    { name: 'Pills', icon: 'noto:pill' },
]
</script>

<template>
    <div class="w-100% flex flex-row items-start justify-center">
        <template v-for="(task, index) in tasks" class="">
            <div class="w-auto text-center items-center">
                <button class="inline-flex items-center justify-center w-10 h-10 rounded-full transition-colors duration-150 focus:shadow-outline
                hover:bg-pink-800
                border border-pink-600">
                    <UIcon :name="task.icon" class="w-5 h-5" />
                </button><br/>
                <span class="text-slate-200/60 text-xs">{{ task.name }}</span>
            </div>
            <span v-if="index < tasks.length - 1" class="p-0 m-0 h-5 grow border-b border-pink-600">&nbsp;</span>
        </template>
    </div>
</template>