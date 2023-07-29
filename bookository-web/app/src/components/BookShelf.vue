<template>
  <AppDrop @drop="move($event, clonedeep(shelf))">
    <AppDrag
      class="shelf"
      :transferData="shelf"
    >
      <div class="flex items-center mb-2 font-bold">
        {{ shelf.name }}
      </div>
      <div class="list-reset">
        <slot/>

        <input
          v-model="newCard"
          type="text"
          class="block p-2 w-full bg-transparent"
          placeholder="+ Добавить книгу"
          @keyup.enter="createCard"
        />
      </div>
    </AppDrag>
  </AppDrop>
</template>


<script setup>
import { ref } from 'vue'
import AppDrag from './AppDrag.vue'
import AppDrop from './AppDrop.vue'
import clonedeep from 'lodash.clonedeep'
import { uuid } from '../utils.js'

const props = defineProps({
  shelf: Object
})

const emit = defineEmits([ 'move-shelf', 'create-card', 'move-card-to-shelf' ])

const move = (fromData, toData) => {
  if (fromData.type === 'card') {
    emit('move-card-to-shelf', {
      card: fromData,
      toShelf: toData
    })
  }


  if (fromData.type === 'shelf') {
    emit('move-shelf', {
      shelf: fromData,
      toShelf: toData
    })
  }
}

const newCard = ref('')

const createCard = () => {
  if (!newCard.value) {
    return
  }

  const payload = {
    type: 'card',
    description: '',
    name: newCard.value,
    id: uuid(),
    shelfId: props.shelf.id,
    color: 'bg-red-200'
  }

  emit('create-card', payload)
}
</script>

<style lang="css">
.shelf {
  @apply bg-gray-200 p-2 mr-4 text-left shadow rounded;
  min-width: 350px;
}
</style>
