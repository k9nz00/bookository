import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    host: true,
    port: 8000, // This is the port which we will use in docker
    watch: {
      usePolling: true
    }
  }
})
