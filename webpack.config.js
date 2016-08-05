var path = require("path");
var webpack = require("webpack");

module.exports = {
    context: path.join(__dirname, 'app', 'assets', 'js'),
    entry: {
        'welcome': './pages/welcome/index.jsx'
    },
    output: {
        path: path.join(__dirname, 'public', 'js'),
        publicPath: '/assets/js/',
        filename: '[name].js'
    },
    module: {
        loaders: [
            {
                test: /\.js|\.jsx$/,
                exclude: /node_modules/,
                loader: "babel-loader",
                query: {
                    presets: [
                        "es2015", "stage-0", "react"
                    ]
                }
            },
            {
                test: /\.less$/,
                loader: "style!css!less"
            },
            {
                test: /\.(jpe?g|png|gif|svg)$/i,
                loader: 'file'
            }
        ]
    },
    externals: [
        {
            'jquery': '$',
            'react': 'React',
            'react-dom': 'ReactDOM',
            'immutable': 'Immutable',
            'classNames': 'classNames'
        }
    ],
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery'
        }),
        new webpack.optimize.UglifyJsPlugin({
            test: /.*/,
            compress: {
                warnings: false
            },
            output: {
                comments: false,
                semicolons: true
            },
            sourceMap: false
        }),
        new webpack.optimize.CommonsChunkPlugin('vendors', 'vendor.js')
    ]
};