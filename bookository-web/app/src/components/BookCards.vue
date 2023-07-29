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

  <BookCardView
    v-if="!!openedCard"
    :card="openedCard"
    @close="closeCard"
  />
</template>

<script setup>
import BookCardView from '../components/BookCardView.vue'
import AppDrag from './AppDrag.vue'
import AppDrop from './AppDrop.vue'
import { ref } from 'vue'
import clonedeep from 'lodash.clonedeep'

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  cards: Array
})

const emit = defineEmits(['move-card'])

const openedCard = ref(null)
const openCard = (card) => {
  openedCard.value = card
}
const closeCard = () => {
  openedCard.value = null
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
