package com.guilhermebury.agalaxyfaraway.domain

import java.util.Collections.emptyList

/**
 * Created by Guilherme Bury on 02/04/2019.
 */

class Film(var title: String,
           var releaseDate: String,
           var cover: Int) {

    var episodeId: Int = 0
    var openingCrawl: String = ""
    var director: String = ""
    var producer: String = ""
    var people: List<Character> = emptyList()
    var planets: List<Planet> = emptyList()
    var starships: List<Starship> = emptyList()
    var vehicles: List<Vehicle> = emptyList()
    var species: List<Specie> = emptyList()
}
