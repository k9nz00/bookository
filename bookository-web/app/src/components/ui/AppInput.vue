<template>
  <div class="app-field-wrapper">
    <label :for="id">{{ label }}</label>
    <input
      v-model="value"
      class="app-field"
      :id="id"
      :placeholder="placeholder"
    >
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  id: {
    type: String,
    default: ''
  },
  placeholder: {
    type: String,
    default: ''
  },
  label: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['update:modelValue'])

const value = ref(props.modelValue)
watch(value, () => {
  emit('update:modelValue', value.value)
})
</script>
