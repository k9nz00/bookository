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
        <BookCards
          :books="books"
          :shelfId="shelf.id"
          @move-card="$emit('move-card')"
        />

        <button
          type="button"
          class="block p-2 w-full text-gray-400 text-left"
          @click="createBookCard"
        >
          + Добавить книгу
        </button>
      </div>
    </AppDrag>
  </AppDrop>
</template>


<script setup>
import AppDrag from './AppDrag.vue'
import AppDrop from './AppDrop.vue'
import BookCards from '../components/BookCards.vue'
import clonedeep from 'lodash.clonedeep'

const props = defineProps({
  shelf: {
    type: Object,
    required: true
  },
  books: {
    type: Array,
    default: () => []
  }
})

const emit = defineEmits([ 'move-shelf', 'create-card', 'move-card-to-shelf', 'move-card' ])

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

const createBookCard = () => {
  emit('create-card', props.shelf.id)
}
</script>

<style lang="css">
.shelf {
  @apply bg-gray-200 p-2 mr-4 text-left shadow rounded;
  min-width: 350px;
}
</style>
