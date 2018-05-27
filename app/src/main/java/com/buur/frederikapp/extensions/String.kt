package com.buur.frederikapp.extensions

import com.buur.frederikapp.controllers.SessionController

fun String.addChampionImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/champion/$this"
}

fun String.addProfileIconImagePath(version: String): String {
    return "http://ddragon.leagueoflegends.com/cdn/$version/img/profileicon/$this.png"
}