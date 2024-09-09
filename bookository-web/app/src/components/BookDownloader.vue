<template>
  <div class="flex flex-col gap-3 items-start">
    <div
      v-for="content in bookContent"
      :key="content.id"
      @click="loadBookContent(content.id)"
    >
      &#11015;
      <span class="underline underline-offset-4 cursor-pointer">
        {{ book.name + '.' + content.format.toLowerCase() + ' '}} <span class="font-bold">({{ content.size }})</span>
      </span>
    </div>
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

