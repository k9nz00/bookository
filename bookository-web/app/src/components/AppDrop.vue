<template>
  <div
    @drop.stop="onDrop"
    @dragover.prevent
    @dragenter.prevent
  >
    <slot/>
  </div>
</template>

<script setup>
const emit = defineEmits(['drop'])

const onDrop = (e) => {
  const transferData = JSON.parse(e.dataTransfer.getData('payload'))
  emit('drop', transferData)
}
</script>
