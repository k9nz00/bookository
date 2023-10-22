<template>
  <div class="board">
    <div class="flex items-start space-x-2 p-2">
      <BookShelf
        v-for="shelf of shelves"
        :key="shelf.name"
        :shelf="shelf"
        :books="books"
        @move-shelf="moveShelf"
        @move-card-to-shelf="moveCardToShelf"
        @create-card="createCard"
        @move-card="moveCard"
      />

      <div class="shelf flex">
        <input
          v-model="newShelfName"
          type="text"
          class="p-2 mr-2 flex-grow"
          placeholder="Добавить полку"
          @keyup.enter="createShelf"
        >
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import BookShelf from '../components/BookShelf.vue'
import defaultBoard from '../default-board.js'
import clonedeep from 'lodash.clonedeep'
import { router } from '../router.js'
import { getBooks } from '../api/index.js'

const books = ref([])
const loadBooks = () => {
  getBooks()
    .then(data => (books.value = data))
    .catch(error => console.log(error))
}

onMounted(() => {
  loadBooks()
  console.log(process.env.API_HOST)
})

const board = ref(defaultBoard)
const shelves = computed(() => {
  return board.value.shelves
})

const newShelfName = ref('')
const createShelf = () => {
  const name = newShelfName.value

  if(!name) {
    return
  }

  board.value.shelves.push({
    name,
    cards: []
  })
  newShelfName.value = ''
}

const moveShelf = ({ shelf, toShelf }) => {
  const shelves = clonedeep(board.value.shelves)

  const shelfIndex = shelves.findIndex(item => item.id === shelf.id)
  const toShelfIndex = shelves.findIndex(item => item.id === toShelf.id)

  shelves.splice(shelfIndex, 1)
  shelves.splice(toShelfIndex, 0, shelf)

  board.value.shelves = shelves
}

const moveCard = ({ card, toCard }) => {
  const shelves = clonedeep(board.value.shelves)

  const toCardShelf = shelves.find(shelf => shelf.id === toCard.shelfId).cards || []
  const toCardIndex = toCardShelf.findIndex(item => item.id === toCard.id)

  board.value.shelves = board.value.shelves.map(shelf => {
    shelf.cards = shelf.cards.filter(item => item.id !== card.id)

    if(shelf.id === toCard.shelfId) {
      card.shelfId = shelf.id
      shelf.cards.splice(toCardIndex, 0, card)
    }

    return shelf
  })
}

const moveCardToShelf = ({ card, toShelf }) => {
  board.value.shelves = board.value.shelves.map(shelf => {
    shelf.cards = shelf.cards.filter(item => item.id !== card.id)

    if(shelf.id === toShelf.id) {
      card.shelfId = shelf.id
      shelf.cards.push(card)
    }

    return shelf
  })
}

const createCard = (shelfId) => {
  router.push(`/books/${shelfId}`)
}
</script>

<style lang="css">
.board {
  @apply p-4 h-full overflow-auto;
  background: no-repeat url('../assets/book-cafe.png');
  background-size: cover;
}
</style>
