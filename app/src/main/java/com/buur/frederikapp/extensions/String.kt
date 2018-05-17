package com.buur.frederikapp.extensions

fun String.addChampionImagePath(): String {
    return "http://ddragon.leagueoflegends.com/cdn/8.8.1/img/champion/$this"
}