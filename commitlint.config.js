module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'references-empty': [0],
  },
  parserPreset: {
    parserOpts: {
      // Указываем, что ссылки начинаются с символа #
      issuePrefixes: ['#']
    }
  }
};
