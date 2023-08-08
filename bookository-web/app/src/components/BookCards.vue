<template>
  <AppDrop
    v-for="card in cards"
    :key="card.id"
    @drop="move($event, clonedeep(card))"
  >
    <AppDrag
      class="card"
      :class="card.color"
      :transferData="card"
      @click="openCard(card)"
    >
      <span class="w-full shrink-0 font-bold">
        {{ card.name }}
      </span>
      <p
        v-if="card.description"
        class="w-full shrink-0 mt-1 text-sm"
      >
        {{ card.description }}
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
  cards: Array
})

const router = useRouter()

const emit = defineEmits(['move-card'])

const openCard = (card) => {
  router.push(`/books/${card.shelfId}/${card.id}`)
}

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
  @apply flex items-center flex-wrap shadow mb-2 py-2 px-2 rounded no-underline;
}
</style>
