import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    findByLocation(location) {
    return http.get(`weather/location/${location}`);
    },
    getForecast(location) {
        return http.get(`forecast/location/${location}`);
        },

    createWeather(temperature){
        return http.post('weather', temperature)
    }

    }