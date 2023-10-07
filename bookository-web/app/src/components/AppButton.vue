<template>
  <button
    type="button"
    class="app-button"
    @click="click"
  >
    <slot />
  </button>
</template>

<script setup>
const emit = defineEmits(['click'])

const click = () => {
  emit('click')
}
</script>
