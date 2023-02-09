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
      <div class="weather-card">
        <h1>{{temperature.location }}</h1>
        <h2>Current Conditions</h2>
        <p> {{temperature.description }} <img :src="`http://openweathermap.org/img/wn/${temperature.icon}@2x.png`" alt="Weather condition icon" /></p>
        <p>Temperature: {{temperature.temperature }}°F</p>
        <p>Humidity: {{temperature.humidity}}</p>
      </div>
     </template>
    </main>
    <footer class="footer">Footer</footer>
  </div>
</template>

<script>

import WeatherService from '../services/WeatherService';


export default {
  data() {
    return {
      searchTerm: '',
      temperature: '',
    };
  },
  methods: {
    async searchForWeather() {
  try {
    const weather = await WeatherService.findByLocation(this.searchTerm);
    console.log(weather.data);
    this.temperature = weather.data;
  } catch (error) {
    console.error(error);
  }
}

  }
};
</script>

<style>
 .container {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: "header" "main" "footer";
  height: 100vh;
  background-image: url('https://images2.alphacoders.com/949/949049.png');
  background-size: cover;
}
.header {
  grid-area: header;
  display: flex;
  justify-content:center;
  align-items: center;
  font-family: monospace, serif;
  height: 20vh;
}
.main {
  grid-area: main;
  height: 60vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
.footer {
  grid-area: footer;
  height: 20vh;
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
  opacity: 80%;
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


</style>
