<script setup lang="ts">
    const props = defineProps<{
        room: any
    }>()
    
    const config = useRuntimeConfig()
    const newTaskName = defineModel()

    async function addTask() {
        const task: any = await $fetch(`${config.public.backendApiUrl}/room/${props.room.roomId}/task/create`, {
            method: "POST",
            body: {
                name: newTaskName.value
            }
        })
        
        props.room.tasks.push(task)
        newTaskName.value = ""
    }
</script>

<template>
    <Card :title="room.name">
        <ul class="px-2 my-1">
            <li v-for="task in room.tasks">
                <input type="checkbox" :id="task.id"/>&nbsp;
                <label :for="task.id">{{task.name}}</label>

                <hr class="my-1" />
            </li>
            <li class="pl-2">
                <input v-model="newTaskName" type="text" class="outline-none" placeholder="Ajouter un élément" @keyup.enter="addTask" />
            </li>
        </ul>
    </Card>
</template>