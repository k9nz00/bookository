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
      <img
        class="book-cover-small"
        alt=""
        :src="coverSrc(book)"
      >
      <div>
        <span class="w-full shrink-0 font-bold">
          {{ book.name }}
        </span>
        <p
          v-if="book.author"
          class="w-full shrink-0 mt-1 text-sm truncate"
        >
          {{ book.author }}
        </p>
      </div>
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
  router.push(`/books/${ props.shelfId }/${ book.id }`)
}

const coverSrc = (book) => {
  return 'data:image/gpeg;base64,' + book.smallPreview
}

const emit = defineEmits([ 'move-card' ])
const move = (card, toCard) => {
  if (card.type === 'card') {
    emit('move-card', {
      card,
      toCard
    })
  }
}
</script>

<style lang="css">
.card {
  @apply flex items-center flex-wrap gap-2 shadow mb-2 py-2 px-2 rounded no-underline bg-green-100;
}

.book-cover-small {
  @apply border border-blue-300 rounded-md;
  background: url('../assets/vue.svg') no-repeat center;
  background-size: 50%;
  width: 50px;
  height: 50px;
}
</style>
