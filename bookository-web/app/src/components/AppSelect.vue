<template>
  <div class="app-select w-full relative">
    <Listbox v-model="selectedLocal">
      <div class="flex item-center">
        <ListboxButton class="listbox-button app-field">
          <span
            v-if="selectedLocal[nameField]"
            class="block truncate">
            {{ selectedLocal[nameField] }}
          </span>

          <span v-else class="block truncate text-gray-400">{{ placeholder }}</span>

          <span class="pointer-events-none absolute inset-y-0 right-0 flex items-center">
            <ChevronUpDownIcon
              class="h-5 w-5 text-gray-400"
              aria-hidden="true"
            />
          </span>
        </ListboxButton>

        <transition
          leave-active-class="transition duration-100 ease-in"
          leave-from-class="opacity-100"
          leave-to-class="opacity-0"
        >
          <ListboxOptions class="listbox-options">
            <ListboxOption v-if="!optionsLocal.length" as="div">
              <li class="relative cursor-default select-none p-2">
                <span class="block truncate">
                  Нет данных для отображения
                </span>
              </li>
            </ListboxOption>

            <ListboxOption
              v-else
              v-for="option in optionsLocal"
              :key="option[valueField]"
              :value="option"
              :disabled="option[disabledField]"
              v-slot="{ active, selected, disabled }"
              as="template"
            >
              <li
                class="relative cursor-default select-none py-2 pl-10 pr-4"
                :class="{
                'bg-blue-100' : active,
                'text-gray-300' : disabled
              }"
              >
                <span
                  class="block truncate"
                  :class="selected ? 'font-bold' : 'font-normal'"
                >
                  {{ option[nameField] }}
                </span>
                <span
                  v-if="selected"
                  class="absolute inset-y-0 left-0 flex items-center pl-3 text-blue-600"
                >
                  <CheckIcon
                    class="h-5 w-5"
                    aria-hidden="true"
                  />
                </span>
              </li>
            </ListboxOption>
          </ListboxOptions>
        </transition>
      </div>
    </Listbox>
  </div>
</template>

<script setup>
import {
  Listbox,
  ListboxButton,
  ListboxOptions,
  ListboxOption,
} from '@headlessui/vue'

import { CheckIcon, ChevronUpDownIcon } from '@heroicons/vue/20/solid'

import { ref, watch, watchEffect } from 'vue'
import clonedeep from 'lodash.clonedeep'

const props = defineProps({
  options: {
    type: Array,
    default: () => []
  },
  selected: {
    type: Object,
    default: () => ({
      name: '',
      id: ''
    })
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
    default: 'Выберите что-нибудь'
  }
})

const emit = defineEmits(['select'])

const optionsLocal = ref(clonedeep(props.options))
const selectedLocal = ref(clonedeep(props.selected))

watchEffect(() => {
  optionsLocal.value = clonedeep(props.options)
})

watch(selectedLocal, () => {
  emit('select', selectedLocal.value)
})
</script>

<style>
.listbox-button {
  @apply w-full text-left;
}

.listbox-options {
  @apply absolute max-h-60 w-full overflow-auto bg-white py-1;
  @apply shadow-lg ring-1 ring-black ring-opacity-5 z-10;
  top: 38px;
  right: 0;
}
</style>
