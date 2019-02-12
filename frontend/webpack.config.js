const path = require('path');
const ManifestPlugin = require('webpack-manifest-plugin');

module.exports = {
    entry: ['./src/main.js', './src/styles/main.scss'],
    output: {
        path: path.resolve(__dirname, 'dist/bundles'),
        filename: '[name].[chunkhash].js',
    },
    resolve: {
        extensions: ['.js']
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                loader: 'babel-loader'
            },
            {
                test: /\.scss$/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: 'styles/[name].css',
                        }
                    },
                    {
                        loader: 'extract-loader'
                    },
                    {
                        loader: 'css-loader?-url'
                    },
                    {
                        loader: 'sass-loader'
                    }
                ]
            }
        ]
    },
    plugins: [
        new ManifestPlugin(),
    ]
};
