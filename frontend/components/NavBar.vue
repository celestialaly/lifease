<script setup lang="ts">
const colorMode = useColorMode()
const isDark = computed({
  get() {
    return colorMode.value === 'dark'
  },
  set() {
    colorMode.preference = colorMode.value === 'dark' ? 'light' : 'dark'
  }
})
function switchColorMode () {
  isDark.value = !isDark
}

const links = [
  [],
  [
  {
    label: 'Dashboard',
    icon: 'i-heroicons-home',
    to: '/'
  }
],
[
  {
    label: 'darkmode',
    icon: '',
    click: switchColorMode
  }
]];
</script>

<template>
  <div class="bg-violet-200 dark:bg-pink-900">
    <div class="container mx-auto">
      <UHorizontalNavigation :links="links">
        <template #default="{ link }">
          <template v-if="link.label == 'darkmode'">
            <ClientOnly>
              <UIcon :name="isDark ? 'i-heroicons-moon-20-solid' : 'i-heroicons-sun-20-solid'" class="w-5 h-5" />
            </ClientOnly>
          </template>
        </template>
      </UHorizontalNavigation>
    </div>
  </div>
</template>