<template>
  <div class="h-full">
    <router-view />
  </div>
</template>


<style>
body, html {
  height: 100%;
}

#app {
  @apply text-sm lg:text-base;
  font-family: Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height: 100vh;
}
</style>
