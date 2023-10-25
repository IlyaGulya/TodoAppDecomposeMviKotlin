// As per https://cashapp.github.io/sqldelight/js_sqlite/

var CopyWebpackPlugin = require('copy-webpack-plugin');
config.plugins.push(
    new CopyWebpackPlugin(
        {
            patterns: [
                  "../../node_modules/@sqlite.org/sqlite-wasm/sqlite-wasm/jswasm/sqlite3.js",
                  "../../node_modules/@sqlite.org/sqlite-wasm/sqlite-wasm/jswasm/sqlite3.wasm",
                  "../../node_modules/@sqlite.org/sqlite-wasm/sqlite-wasm/jswasm/sqlite3-opfs-async-proxy.js",
            ]
        }
    )
);
