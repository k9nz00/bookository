import express from 'express'
import path from 'path'

import { fileURLToPath } from 'url'
import { dirname } from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

const app = express();

app.use(express.static(path.join(__dirname, 'build')));

app.get('/*', function (req, res) {
  res.sendFile(path.join(__dirname, 'build', 'index.html'));
});

const APP_PORT = parseInt(process.env.APP_PORT, 10) || 3000;

app.listen(APP_PORT, (err) => {
  if (err) {
    throw err;
  }
  console.log(`> Ready on http://localhost:${APP_PORT}`);
});
