package com.buur.frederikapp.extensions

const val ddragonVersion = "8.8.1"

fun String.addChampionImagePath(): String {
    return "http://ddragon.leagueoflegends.com/cdn/$ddragonVersion/img/champion/$this"
}

fun String.addProfileIconImagePath(): String {
    return "http://ddragon.leagueoflegends.com/cdn/$ddragonVersion/img/profileicon/$this.png"
}