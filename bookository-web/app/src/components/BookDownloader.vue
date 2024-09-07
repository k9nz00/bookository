<template>
  <div class="flex gap-3 items-center">
    <button
      v-for="content in bookContent"
      :key="content.id"
      class="app-button-link"
      @click="loadBookContent(content.id)"
    >
      {{ book.name + '.' + content.format.toLowerCase() + ' '}} <span class="font-bold">{{ content.size }}</span>
    </button>
  </div>
</template>

<script setup>
import { API_HOST } from '../constants.js'

const props = defineProps({
  book: {
    type: Object,
    required: true
  },
  bookContent: {
    type: Object,
    required: true
  }
})

const loadBookContent = (contentId) => {
  let name
  return fetch(`${ API_HOST }/books/${props.book.id}/content/${contentId}`, {
    method: 'GET',
    mode: 'cors'
  })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      name = decodeURIComponent(response.headers.get('Real-File-Name'))
      return response.blob()
    })
    .then(result => {
      const url = window.URL.createObjectURL(result);
      const a = document.createElement('a')
      a.href = url
      a.download = name
      document.body.appendChild(a)
      a.click()
      a.remove()
      window.URL.revokeObjectURL(url)
    })
}

</script>

