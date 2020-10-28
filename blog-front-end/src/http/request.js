import axios from 'axios'
import urls from '@/http/urls'
import store from "@/store/index";
import router from "@/router/index";

const instance = axios.create({
    baseURI: urls.baseUrl,
    timeout: 5000,
});
instance.interceptors.request.use((config)=>{
    if(store.state.token){
        config.headers.authorization = store.state.token;
    }
    return config;
});

instance.interceptors.response.use(
    (response) =>{
        return response;
    },
    (error) =>{
        if(error.response){
            switch (error.response.status){
                case 401:
                    router.replace({
                        path: "login",
                        query: {redirect: router.currentRoute.fullPath},
                    });
            }
        }
        return Promise.reject(error.response);
    }
)

export default {
    getArticles(page,limit){
        return instance.get(urls.articles,{
            params:{
                page: page,
                limit: limit
            }
        }).then(res=>res.data);
    }
}
