<template>
  <div>
    <label for="input-file">
      <img
        class="book-cover"
        alt=""
        :src="cover"
      >
    </label>

    <input
      type="file"
      id="input-file"
      class="input-file"
      :disabled="disabled"
      @change="onUploadCover($event.target.files)"
    >
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useCover } from '../hooks/useCover.js'

const props = defineProps({
  bookId: Number,
  disabled: {
    type: Boolean,
    default: false
  }
})

const { updateCover } = useCover()

// TODO: SPBR-53 допустимое расширение файла
const src = ref('')
const onUploadCover = (uploadedFiles) => {
  const file = uploadedFiles[0]
  const reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onload = function () {
    src.value = reader.result

    updateCover(props.bookId, file)
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
  min-width: 292px;
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
