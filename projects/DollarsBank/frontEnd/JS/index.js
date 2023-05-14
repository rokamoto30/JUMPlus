function getPokemon() {
    const pokemonName = document.getElementById("pokemonNameGet").value;
    if (!pokemonName) {
        alert("Please enter a pokemon name");
        return;
    }

    const endpoint = new URL(`https://pokeapi.co/api/v2/pokemon/${ pokemonName }`);
    console.log(endpoint);

}