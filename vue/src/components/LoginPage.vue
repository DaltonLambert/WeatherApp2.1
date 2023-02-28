<template>
    <header>
        <h1>LOGIN</h1>
    </header>
    <main>
        <form>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username"><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password"><br>

            <button type="submit">Login</button>
        </form>
    </main>
    <footer>
        <p>&copy; 2022 Dalton's Weather App</p>
    </footer>
</template>

<style scoped>

#username {
    font-size: 20px;
    margin: 10px;
}

#password {
    font-size: 20px;
    font-weight: bold;
    margin: 10px;
    margin-left:15px;
    height: 37px;
    width: 255px;
    border-color: black;
    border-radius: 5px;
}

h1 {
    color: black;
}
</style>