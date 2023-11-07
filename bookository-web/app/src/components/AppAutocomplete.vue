<template>
  <div class="app-field-wrapper w-full relative">
    <label>{{ label }}</label>
    <div class="relative">
      <Combobox v-model="selectedOptionsLocal" multiple>
        <div class="cursor-pointer d-flex overflow-hidden bg-white text-left">
          <ComboboxInput
            class="app-field w-full text-sm leading-5 text-gray-900"
            :displayValue="displayValue"
            :placeholder="placeholder"
            @change="query = $event.target.value"
          />

          <ComboboxButton
            class="absolute inset-y-0 right-0 flex items-center"
          >
            <ChevronUpDownIcon
              class="h-5 w-5 text-gray-400"
              aria-hidden="true"
            />
          </ComboboxButton>
        </div>

        <TransitionRoot
          leave="transition ease-in duration-100"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
          @after-leave="query = ''"
        >
          <ComboboxOptions
            class="z-10 absolute bg-white max-h-60 w-full overflow-auto bg-white py-1 text-base shadow-lg"
          >
            <div
              v-if="filteredOptions.length === 0 && query !== ''"
              class="relative cursor-default select-none py-2 px-4 text-gray-700"
            >
              Ничего не найдено
            </div>

            <ComboboxOption
              v-for="option in filteredOptions"
              as="template"
              :key="option.id"
              :value="option"
              v-slot="{ selected, active }"
            >
              <li
                class="relative cursor-default select-none py-2 pl-10 pr-4"
                :class="{ 'bg-blue-100': active }"
              >
                <span
                  class="block text-sm truncate"
                  :class="{ 'font-medium': selected, 'font-normal': !selected }"
                >
                  {{ option.name }}
                </span>
                <span
                  v-if="selected"
                  class="absolute inset-y-0 left-0 flex items-center pl-3 text-blue-600"
                >
                  <CheckIcon class="h-5 w-5" aria-hidden="true" />
                </span>
              </li>
            </ComboboxOption>
          </ComboboxOptions>
        </TransitionRoot>
      </Combobox>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import {
  Combobox,
  ComboboxInput,
  ComboboxButton,
  ComboboxOptions,
  ComboboxOption,
  TransitionRoot,
} from '@headlessui/vue'
import CheckIcon from './CheckIcon.vue'
import ChevronUpDownIcon from './ChevronUpDownIcon.vue'

const props = defineProps({
  options: {
    type: Array,
    default: () => []
  },
  selectedOptions: {
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
  },
  placeholder: {
    type: String,
    default: 'Выберите что-нибудь'
  },
  label: {
    type: String,
    default: ''
  }
})

const emit = defineEmits(['select'])

const query = ref('')
const selectedOptionsLocal = ref(props.selectedOptions)

const displayValue = (() => {
  return (selectedOptionsLocal.value || []).map(item => item.name).join(', ')
})

const filteredOptions = computed(() =>
  query.value === ''
    ? props.options
    : props.options.filter((option) =>
      option.name
        .toLowerCase()
        .replace(/\s+/g, '')
        .includes(query.value.toLowerCase().replace(/\s+/g, ''))
    )
)

watch(selectedOptionsLocal, () => {
  emit('select', selectedOptionsLocal.value.map(item => item.id))
})
</script>
