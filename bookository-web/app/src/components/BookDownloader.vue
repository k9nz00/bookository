<template>
  <div class="flex flex-col gap-3 items-start">
    <div
      v-for="content in bookContent"
      :key="content.id"
      @click="getBookContent(book.id, content.id)"
    >
      &#11015;
      <span class="underline underline-offset-4 cursor-pointer">
        {{ book.name + '.' + content.format.toLowerCase() + ' '}} <span class="font-bold">({{ content.size }})</span>
      </span>
    </div>
  </div>
</template>

<script setup>
import { getBookContent } from '../api/index.js'

defineProps({
  book: {
    type: Object,
    required: true
  },
  bookContent: {
    type: Object,
    required: true
  }
})
</script>
