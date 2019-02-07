module.exports = {
    setupFilesAfterEnv: ["./setupTests.js"],
    testURL: 'http://example.com/test.html',
    snapshotSerializers: [
        'enzyme-to-json/serializer',
    ],
    moduleFileExtensions: [
        'js',
        'jsx',
    ],
    testPathIgnorePatterns: [
        '<rootDir>/node_modules/',
        'coverage',
    ],
    testMatch: [
        '<rootDir>/src/**/*.test.{js,jsx}',
    ],
    verbose: true,
};
