<template>
  <textarea
    v-model="value"
    class="border border-gray-300 rounded-md p-5"
    :rows="rows"
    :placeholder="placeholder"
  />
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  rows: {
    type: Number,
    default: 5
  },
  placeholder: {
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
