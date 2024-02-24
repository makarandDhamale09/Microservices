package com.myproject.service

import com.myproject.domain.Player
import com.myproject.domain.Team
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class PlayerService {
    private val players = ArrayList<Player>()
    val id = AtomicInteger(0)

    @PostConstruct
    private fun init() {
        players.add(Player(id.incrementAndGet(), "MS Dhoni", Team.CSK))
        players.add(Player(id.incrementAndGet(), "Rohit Sharma", Team.MI))
        players.add(Player(id.incrementAndGet(), "Jasprit Bumrah", Team.MI))
        players.add(Player(id.incrementAndGet(), "Rishabh Pant", Team.DC))
        players.add(Player(id.incrementAndGet(), "Suresh Raina", Team.CSK))
    }

    fun findAll(): ArrayList<Player> {
        return players
    }

    fun findOne(id: Int): Player? {
        return players.find { it.id == id }
    }

    fun createPlayer(
        name: String,
        team: Team,
    ): Player {
        val player = Player(id.incrementAndGet(), name, team)
        players.add(player)
        return player
    }

    fun deletePlayer(id: Int): Player {
        val player = players.find { it.id == id } ?: throw IllegalArgumentException("Player with $id not present")
        println("Player to be deleted $player")
        players.remove(player)
        return player
    }

    fun updatePlayer(
        id: Int,
        name: String,
        team: Team,
    ): Player {
        val updatedPlayer = Player(id, name, team)
        val player = players.find { it.id == id } ?: throw IllegalArgumentException("Invalid Player id $id")

        val index = players.indexOf(player)
        players[index] = updatedPlayer
        return updatedPlayer
    }
}
