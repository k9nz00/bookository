<template>
  <AppModal @close="$emit('close')">
    <div class="card-view">
      <div class="card-view__name">
        <input
          type="text"
          class="p-2 w-full mr-2 block text-xl font-bold"
          :value="card.name"
        >

        <textarea
          class="card-view__description"
          :value="card.description"
        />
      </div>
    </div>
  </AppModal>
</template>

<script setup>
import AppModal from '../components/AppModal.vue'

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  card: Object
})
</script>

<style>
.card-view {
  @apply relative flex flex-row bg-white;
  @apply mx-4 m-32 py-4 text-left rounded shadow-md;
}

.card-view .card-view__name {
  @apply flex flex-col flex-grow items-start justify-between px-4
}

.card-view .card-view__description {
  @apply relative w-full bg-transparent px-2 ;
  @apply mt-2 h-64 leading-normal;
}
</style>
