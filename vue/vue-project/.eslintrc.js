module.exports = {
    root: true,
    env: {
      node: true,
    },
    extends: [
      'plugin:vue/vue3-essential',
      'eslint:recommended',
      '@vue/prettier',
    ],
    parserOptions: {
      parser: 'babel-eslint',
    },
    rules: {
      // 여기에 사용자 정의 규칙을 추가할 수 있습니다.
    },
  };