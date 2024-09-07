<template>
  <button
    :type="type"
    class="app-button bordered"
    @click="click"
  >
    <Component
      v-if="iconComponent"
      :is="iconComponent"
      class="h-5 w-5"
    />
    <slot />
  </button>
</template>

<script setup>
import { computed } from 'vue'
import ArrowLeftIcon from './icons/ArrowLeftIcon.vue'

const props = defineProps({
  type: {
    type: String,
    default: 'button'
  },
  icon: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['click'])

const icons = {
  ArrowLeftIcon: ArrowLeftIcon
}

const iconComponent = computed(() => {
  return icons[props.icon]
})

const click = () => emit('click')
</script>
