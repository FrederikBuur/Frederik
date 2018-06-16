package com.buur.frederikapp.devutility.extensions

fun String.addChampionImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/champion/$this"
}

fun String.addProfileIconImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/profileicon/$this.png"
}

fun String.addItemIconImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/item/$this.png"
}

fun String.addSummonerSpellIconImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/spell/$this.png"
}