<template>
  <div
    draggable="true"
    @dragstart.self="onDrag"
    @dragover.prevent
    @dragenter.prevent
  >
    <slot/>
  </div>
</template>

<script setup>
const props = defineProps({
  transferData: Object
})

const onDrag = (e) => {
  e.dataTransfer.effectAllowed = 'move'
  e.dataTransfer.dropEffect = 'move'

  e.dataTransfer.setData('payload', JSON.stringify(props.transferData))
}
</script>
