const path = require('path');
const ManifestPlugin = require('webpack-manifest-plugin');

module.exports = {
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, 'dist/bundles'),
        filename: '[name].[chunkhash].js',
    },
    resolve: {
        extensions: ['.js']
    },
    module: {
        rules: [
            { test: /\.js$/, loader: 'babel-loader' }
        ]
    },
    plugins: [
        new ManifestPlugin(),
    ]
};
