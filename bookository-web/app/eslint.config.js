import js from '@eslint/js'

export default [
  js.configs.recommended,
  {
    languageOptions: {
      globals: {
        describe: false,
        test: false,
        expect: false,
        afterEach: false
      }
    },
    rules: {
      'semi': 'off',
      'prefer-const': 'error',
      'eol-last': 'error',
      'indent': [ 'error', 2 ],
      'linebreak-style': [ 'error', 'unix' ],
      'quotes': [ 'error', 'single' ],
    },
    ignores: ['/dist/', 'config/*', '/node_modules/']
  }
]
