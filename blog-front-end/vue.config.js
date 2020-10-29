module.exports = {
    devServer:{
        port: 8888,
        proxy: {
            "/api":{
                target: "http://localhost:9000/api/",
                changeOrigin: true,
                pathRewrite: {
                    "^/api": ""
                }
            }
        }
    }
}
