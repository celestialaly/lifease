<script setup lang="ts">
    defineProps<{
        title: string
    }>()
</script>

<template>
    <div class="bg-white dark:bg-gray-800 border border-gray-200 shadow h-auto mb-4">
        <div class="px-2 py-0.5 bg-pink-300 dark:bg-pink-900">
            <h2 class="text-lg text-gray-900/80 dark:text-white/90">{{ title }}</h2>
        </div>

        <slot></slot>
    </div>
</template>