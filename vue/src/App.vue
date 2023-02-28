<template>
  <nav class="nav-container">
    <div class="navbar-links">
      <router-link to="/">Home</router-link> |
      <router-link to="/login">Login</router-link>
    </div>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.nav-container {
  background-image: linear-gradient(to left, #4f3aaf, #9db3b9);
}

.navbar-links {
  padding: 25px;
  align-items: center;
}

.navbar-links a {
  font-weight: bold;
  color: black;
  font-size: 1.5rem;
  margin-right: 5px;
  margin-left: 5px;
}

.navbar-links a.router-link-exact-active {
  color: #42b983;
}
</style>
