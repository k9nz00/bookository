<template>
  <AppDrop
    v-for="book in books"
    :key="book.id"
    @drop="move($event, clonedeep(book))"
  >
    <AppDrag
      class="card"
      :transferData="book"
      @click="openCard(book)"
    >
      <span class="w-full shrink-0 font-bold">
        {{ book.name }}
      </span>
      <p
        v-if="book.author"
        class="w-full shrink-0 mt-1 text-sm truncate"
      >
        {{ book.author }}
      </p>
    </AppDrag>
  </AppDrop>
</template>

<script setup>
import AppDrag from './AppDrag.vue'
import AppDrop from './AppDrop.vue'
import clonedeep from 'lodash.clonedeep'
import { useRouter } from 'vue-router'

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  shelfId: {
    type: String,
    required: true
  },
  books: {
    type: Array,
    default: () => []
  }
})

const router = useRouter()
const openCard = (book) => {
  router.push(`/books/${props.shelfId}/${book.id}`)
}

const emit = defineEmits(['move-card'])
const move = (card, toCard) => {
  if(card.type === 'card') {
    emit('move-card', {
      card,
      toCard
    })
  }
}
</script>

<style lang="css">
.card {
  @apply flex items-center flex-wrap shadow mb-2 py-2 px-2 rounded no-underline bg-green-100;
}
</style>
