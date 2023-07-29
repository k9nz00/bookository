<template>
  <div class="app-modal">
    <div class="app-modal__wrapper">
      <div class="app-modal__body">
        <slot />
      </div>
    </div>
  </div>
</template>

<style>
.app-modal {
  @apply absolute;
  @apply flex justify-center items-center;
  background: rgba(0, 0, 0, 0.5);
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

.app-modal__wrapper {
  min-width: 50%;
}
</style>
