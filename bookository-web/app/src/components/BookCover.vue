<template>
  <div :class="isMobile ? 'block md:hidden' : 'hidden md:block'">
    <label :for="id">
      <img class="book-cover" alt="" :src="src" >
    </label>

    <input
      type="file"
      class="input-file"
      :id="id"
      @change="onUploadCover($event.target.files)"
    >
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'

const props = defineProps({
  preview: {
    type: String,
    default: ''
  },
  isMobile: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['update:modelValue'])

const id = computed(() => {
  return props.isMobile ? 'input-mobile' : 'input-desktop'
})

// TODO: допустимое расширение файла
const src = ref('')
const onUploadCover = (uploadedFiles) => {
  const file = uploadedFiles[0]
  let reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onload = function() {
    src.value  = reader.result

    emit('update:modelValue', file)
  }
}

onMounted(() => {
  src.value = props.preview
})
</script>

<style>
.book-cover {
  @apply border border-blue-300 rounded-md;
}

.book-cover img {
  min-height: 300px;
  min-width: 250px;
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
