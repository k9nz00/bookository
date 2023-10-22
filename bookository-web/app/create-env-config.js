import fs from 'fs'
import path from 'path'

import { fileURLToPath } from 'url'
import { dirname } from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

// return function, which return values by env variable name from publicRuntimeEnv
// the reasons: deny (complicate) change publicRuntimeEnv on client side
const getEnvBuilder = (publicRuntimeEnv) => (name) => publicRuntimeEnv[name];

const publicRuntimeEnv = JSON.stringify({
  API_HOST: process.env.API_HOST
});

const scriptBody = `window._getEnv_=(${getEnvBuilder.toString()})(${publicRuntimeEnv});`;

// There is /app/build path was created on build time
fs.writeFile(path.join(__dirname, 'build', 'get-env.js'), scriptBody, function (error) {
  if (error) {
    console.log(error);
    return;
  }
  console.log('Success! get-env.js was created');
});
