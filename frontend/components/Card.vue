<script setup lang="ts">
    defineProps<{
        title: string
    }>()
</script>

<template>
    <div class="bg-white border border-gray-200 shadow h-auto mb-4">
        <div class="px-2 py-0.5 bg-gradient-to-r from-pink-300/75 from-10% to-slate-50">
            <h2 class="text-lg text-gray-900/80">{{ title }}</h2>
        </div>

        <slot></slot>
    </div>
</template>