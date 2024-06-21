<template>
  <div class="flex gap-3 items-center">
    <p class="inline-block">
      Скачать в формате:
    </p>
    <AppButton
      class="app-button-link"
      @click="downloadBookContent"
    >
      {{ bookContent.format }}
    </AppButton>
  </div>
</template>

<script setup>
import AppButton from './AppButton.vue'
import { getBookContent } from '../api/index.js'

const props = defineProps({
  bookName: {
    type: String,
    required: true
  },
  bookContent: {
    type: Object,
    required: true
  }
})

const downloadBookContent = () => {
  const bookId = props.bookContent.bookId
  const contentId = props.bookContent.id

  getBookContent(bookId, contentId).then((response) => {
    const binaryString = window.atob(response)
    const textString = new TextDecoder().decode(new Uint8Array([...binaryString].map(char => char.charCodeAt(0))))
    const blob = new Blob([textString], { type: 'text/plain' })

    const objectUrl = URL.createObjectURL(blob)
    const link = document.createElement('a')
    link.setAttribute('href', objectUrl)

    const fileName = `${props.bookName || 'без_названия'}.${props.bookContent.format.toLowerCase()}`

    link.setAttribute('download', fileName)
    link.style.display = 'none'

    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  })
}
</script>

