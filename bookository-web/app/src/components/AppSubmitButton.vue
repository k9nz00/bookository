<template>
  <button
    type="submit"
    class="app-button"
    @click="click"
  >
    <slot />
  </button>
</template>

<script setup>
const emit = defineEmits(['submit'])

const click = () => {
  emit('submit')
}
</script>
