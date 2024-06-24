<template>
  <ListboxOptions class="listbox-options">
    <ListboxOption
      v-for="option in displayedOptions"
      :value="option[valueField]"
      :key="option[valueField]"
      :disabled="option[disabledField]"
      v-slot="{ active, selected, disabled }"
      as="template"
    >
      <li
        class="listbox-option"
        :class="{'bg-blue-100' : active, 'text-gray-500' : disabled }"
      >
          <span class="block truncate" :class="selected ? 'font-bold' : 'font-normal'">
            {{ option[nameField] }}
          </span>
        <span
          v-if="selected"
          class="listbox-option__icon-selected"
        >
            <CheckIcon
              v-if="!option[disabledField]"
              class="h-5 w-5"
              aria-hidden="true"
            />
          </span>
      </li>
    </ListboxOption>
  </ListboxOptions>
</template>

<script setup>
import { computed } from 'vue'

import {
  ListboxOptions,
  ListboxOption
} from '@headlessui/vue'

import CheckIcon from './CheckIcon.vue'

const props = defineProps({
  options: {
    type: Array,
    default: () => []
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
  }
})


const emptyOption = {
  [props.nameField]: 'Нет доступных вариантов для выбора',
  [props.valueField]: 'Нет доступных вариантов для выбора',
  [props.disabledField]: true
}

const displayedOptions = computed(() => {
  return props.options.length
    ? props.options
    : [ emptyOption ]
})
</script>

<style scoped>
.listbox-options {
  @apply absolute max-h-60 w-full overflow-auto bg-white py-1;
  @apply shadow-lg ring-1 ring-black ring-opacity-5 z-10;
  top: 38px;
  right: 0;
}

.listbox-option {
  @apply relative cursor-default select-none py-2 pl-10 pr-4;
}

.listbox-option__icon-selected {
  @apply absolute inset-y-0 left-0 flex items-center pl-3 text-blue-600;
}
</style>
