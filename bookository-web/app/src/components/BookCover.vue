<template>
  <div :class="isMobile ? 'block md:hidden' : 'hidden md:block'">
    <label :for="id">
      <img
        class="book-cover"
        alt=""
        :src="cover"
      >
    </label>

    <input
      type="file"
      class="input-file"
      :disabled="disabled"
      :id="id"
      @change="onUploadCover($event.target.files)"
    >
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { updateBookCover } from '../api/index.js'
import { useCover } from '../hooks/useCover.js'

const props = defineProps({
  bookId: {
    type: Number,
    required: true
  },
  isMobile: {
    type: Boolean,
    default: false
  },
  disabled: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits([ 'update:modelValue' ])

const id = computed(() => {
  return props.isMobile ? 'input-mobile' : 'input-desktop'
})

const updateCover = (file) => {
  updateBookCover(props.bookId, file)
}

// TODO: допустимое расширение файла
const src = ref('')
const onUploadCover = (uploadedFiles) => {
  const file = uploadedFiles[0]
  const reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onload = function () {
    src.value = reader.result

    updateCover(file)
  }
}

const { cover, loadCover } = useCover()
onMounted(() => {
  loadCover(props.bookId)
})
</script>

<style>
.book-cover {
  @apply border border-blue-300 rounded-md;
  height: 438px;
  width: 292px;
  background: url('../assets/vue.svg') no-repeat center;
  background-size: 50%;
}

.input-file {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}
</style>
