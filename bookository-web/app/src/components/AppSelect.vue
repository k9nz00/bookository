<template>
  <div class="app-select w-full relative">
    <Listbox>
      <div class="flex item-center">
        <ListboxButton class="listbox-button">
          <span class="block truncate">{{ selected }}</span>
          <span class="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
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
          <ListboxOptions
            class="listbox-options"
          >
            <ListboxOption
              v-for="option in options"
              :key="option[valueField]"
              :value="option[valueField]"
              v-slot="{ active, selected }"
              as="template"
            >
              <li
                class="relative cursor-default select-none py-2 pl-10 pr-4"
                :class="{ 'bg-blue-100' : active }"
              >
                <span
                  class="block truncate"
                  :class="selected ? 'font-medium' : 'font-normal'"
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
  }
})
</script>

<style>
.listbox-button {
  @apply w-full cursor-default text-left sm:text-sm;
  @apply focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white;
  @apply focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300;
}

.listbox-options {
  @apply absolute max-h-60 w-full overflow-auto bg-white py-1 text-base;
  @apply shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm z-10;
  top: 38px;
  right: 0;
}
</style>
