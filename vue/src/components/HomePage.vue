<template>
  <div class="container">
    <header class="header">
      <div class="header-content">
        <h1>Dalton's Weather App</h1>
        <div class="search-container">
          <input class="search-bar" type="text" placeholder="Search for a city" v-model="searchTerm">
          <button @click="searchForWeather">Search</button>
        </div>
      </div>
    </header>
    <main class="main">
      <template v-if="temperature">
        <div class="main-content">
          <div class="weather-card">
            <h1>{{ temperature.location }} <span class="current-time">{{ currentTime }}
            <button v-on:click.prevent="addToFavorites(temperature)">Favorite</button></span></h1>
            <h2>Current Conditions</h2>
            <div class="weather-components">
              <p> {{ temperature.description }}
                <img class="icon" :src="`http://openweathermap.org/img/wn/${temperature.icon}@2x.png`"
                  alt="Weather condition icon" />
              </p>
              <p>Temperature: {{ temperature.temperature }}°F</p>
              <p>Humidity: {{ temperature.humidity }}</p>
              
            </div>
          </div>
          <div class="forecast-card-container">
            <div class="forecast-card" v-for="(day, index) in forecast" :key="index">
              <h1>
                <img class="icon2" :src="`http://openweathermap.org/img/wn/${day.icon}@2x.png`"
                  alt="Weather condition icon" />
              </h1>
              <h1>{{ day.high }}°F</h1>
              <h1>{{ day.low }}°F</h1>
            </div>
          </div>
        </div>
      </template>
    </main>
    <footer class="footer">
    </footer>
  </div>
</template>

<script>

import WeatherService from '../services/WeatherService';


export default {
  data() {
    return {
      searchTerm: '',
      temperature: '',
      currentTime: '',
      timezone: '',
      forecast: []
    };
  },
  mounted() {
    setInterval(() => {
      let date = new Date();
      date.setUTCSeconds(date.getUTCSeconds() + this.temperature.timezone + 18000);
      this.currentTime = date.toLocaleTimeString();
    }, 1000);
  },
  methods: {
    async searchForWeather() {
      try {
        const weather = await WeatherService.findByLocation(this.searchTerm);
        console.log(weather.data);
        this.temperature = weather.data;
        this.timezone = weather.data.timezone;
        const forecast = await WeatherService.getForecast(this.searchTerm);
        this.forecast = forecast.data;
      } catch (error) {
        console.error(error);
      }
    },
    addToFavorites(temperature){
      if (confirm("Add Weather to your favorites?"))
      WeatherService.createWeather(temperature).then((response) => {
        alert("Weather added to favorites!")
        console.log(response)
      });
    }
  }
};
</script>

<style>
body {
  margin: 0;
}

 .container {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: "header" "main" "footer";
  background-image: url('https://i.pinimg.com/originals/31/68/c0/3168c09eeeb529be5b321575fe56b02b.gif');
  background-size: cover;
  height: 100%;
}
.header {
  grid-area: header;
  display: flex;
  justify-content:center;
  align-items: center;
  font-family: monospace, serif;
  height: 20vh;
  padding-bottom: 50px;
}
.main {
  grid-area: main;
  height: 70vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Montserrat;
}
.main-content {
  display: flex;
  flex-direction: column;
}
.footer {
  grid-area: footer;
  height: 10vh;
  display: flex;
  align-items: center;
  justify-content: center;
  
}

input[type="text"] {
  padding: 0.5em;
  margin-right: 0.5em;
  border: 1;
  border-radius: 5px;
  font-size: 16px;
}

button {
  padding: 0.5em 1em;
  background-color: #fff;
  border: 1;
  border-radius: 5px;
  color: #333;
  cursor: pointer;
  font-size: 16px;
}

.weather-card {
  background-color: black;
  border-radius: 3rem;
  box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
  padding: 2rem;
  text-align: center;
  width: 400px;
  height: 400px;
  margin: 2rem auto;
  opacity: 85%;
}

.forecast-card {
  background-color: black;
  
  box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
  padding: 2rem;
  text-align: center;
  width: 200px;
  height: 200px;
  
  opacity: 85%;
  margin-right: 20px;
  
}
.forecast-card-container {
  display: flex;

}
h1 {
  color: white;
}

h2 {
  color: white;
}

p{
  color: white;
}

.weather-components{
  font-size:22pt;
}

.icon{
  height: 120px;
  width: 120px;
}

.current-time {
  font-size: 20px;
  color: gray;
}

.icon2{
  height: 100px;
  width: 100px;
}
.forecast-card-container h1 {
  margin: 0;
}

</style>
