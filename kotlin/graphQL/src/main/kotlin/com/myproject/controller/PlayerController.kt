package com.myproject.controller

import com.myproject.domain.Player
import com.myproject.domain.Team
import com.myproject.service.PlayerService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class PlayerController(val playerService: PlayerService) {
    @QueryMapping
    fun findAll(): ArrayList<Player> {
        return playerService.findAll()
    }

    @QueryMapping
    fun findOne(
        @Argument id: Int,
    ): Player? {
        return playerService.findOne(id)
    }

    @MutationMapping
    fun create(
        @Argument name: String,
        @Argument team: Team,
    ): Player {
        return playerService.createPlayer(name, team)
    }

    @MutationMapping
    fun delete(
        @Argument id: Int,
    ): Player {
        return playerService.deletePlayer(id)
    }

    @MutationMapping
    fun update(
        @Argument id: Int,
        @Argument name: String,
        @Argument team: Team,
    ): Player {
        return playerService.updatePlayer(id, name, team)
    }
}
