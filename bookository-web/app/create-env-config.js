import fs from 'fs'
import path from 'path'

import { fileURLToPath } from 'url'
import { dirname } from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

const getEnvBuilder = (publicRuntimeEnv) => (name) => publicRuntimeEnv[name];

const publicRuntimeEnv = JSON.stringify({
  API_HOST: process.env.API_HOST
});

const scriptBody = `window._getEnv_=(${getEnvBuilder.toString()})(${publicRuntimeEnv});`;

fs.writeFile(path.join(__dirname, 'dist', 'get-env.js'), scriptBody, function (error) {
  if (error) {
    console.log(error);
    return;
  }
  console.log('get-env.js was created');
});
