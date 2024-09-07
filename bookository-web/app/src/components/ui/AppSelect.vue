<template>
  <div class="app-field-wrapper app-select w-full">
    <label>{{ label }}</label>
    <div class="relative">
      <Listbox v-model="selectedLocal">
        <ListboxButton class="listbox-button app-field">
          <span
            class="block truncate"
            :class="{'text-gray-400' : !selectedLocalName}"
          >
            {{ selectedLocalName || placeholder }}
          </span>

          <span class="pointer-events-none absolute inset-y-0 right-0 flex items-center">
            <ChevronUpDownIcon class="h-5 w-5 text-gray-400"/>
          </span>
        </ListboxButton>

        <transition
          leave-active-class="transition duration-100 ease-in"
          leave-from-class="opacity-100"
          leave-to-class="opacity-0"
        >
          <AppSelectOptions
            :options="options"
            :value-field="valueField"
            :name-field="nameField"
            :disabled-field="disabledField"
          />
        </transition>
      </Listbox>
    </div>

  </div>
</template>

<script setup>
import {
  Listbox,
  ListboxButton
} from '@headlessui/vue'

import AppSelectOptions from './AppSelectOptions.vue'
import ChevronUpDownIcon from './icons/ChevronUpDownIcon.vue'

import { ref, computed, watch } from 'vue'

const props = defineProps({
  options: {
    type: Array,
    default: () => []
  },
  selected: {
    type: String,
    default: ''
  },
  nameField: {
    type: String,
    default: 'name'
  },
  valueField: {
    type: String,
    default: 'id'
  },
  disabledField: {
    type: String,
    default: 'disabled'
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

const emit = defineEmits([ 'update:selected' ])

const selectedLocal = ref(props.selected)

const selectedLocalName = computed(() => {
  if(!selectedLocal.value || !props.options.length) {
    return ''
  }

  const option = props.options.find(item => item[props.valueField] === selectedLocal.value)
  return option && option[props.nameField] || ''
})

watch(selectedLocal, () => {
  emit('update:selected', selectedLocal.value)
})
</script>

<style>
.listbox-button {
  @apply w-full text-left;
}
</style>
