package com.techytribe.gameapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping(path = "/api/v1/player")
public class PlayerController {
    // We use this type of list to handel multiple request, create duplicate of list, put data in different list then
    //marge it
    CopyOnWriteArrayList <Player> listOfPlayers = new CopyOnWriteArrayList<>();
//To create game we use post mapping
    @PostMapping
    public Player createPlayer(Player incomingPlayer){
        listOfPlayers.add(incomingPlayer);
        return incomingPlayer;

    }
}
