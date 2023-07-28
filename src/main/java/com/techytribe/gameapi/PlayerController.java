package com.techytribe.gameapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping(path = "/api/v1/player")
public class PlayerController {
    // We use this type of list to handel multiple request, create duplicate of list, put data in different list then
    //marge it
    CopyOnWriteArrayList <Player> listOfPlayers = new CopyOnWriteArrayList<>();
//To create game we use post mapping
    @PostMapping
    //sbringboot do not know which data use to make it player object so
    // use @RequsetBodey whenever you receive request you have to check the body
    // now sbringboot will convert that string into player "deserialization"
    public Player createPlayer(@RequestBody Player incomingPlayer){
        listOfPlayers.add(incomingPlayer);
        return incomingPlayer;

    }
    // we want to get all the players
    @GetMapping
    public List <Player> getAllPlayers(){
        return listOfPlayers;
    }
    // how we get specific player
    @GetMapping(path ="/{id}")// So that sbringboot will not confuse
    // we should write pathVariable to get the id otherwise will get null
    public Player getSpecificPlayer(@PathVariable String id){
        //this is another logic
        //Player newPlayer = new Player();
        //newPlayer.id = id;
        //return newPlayer;
        // here we will write our logic how to get specific player
        //filter() will check the condition
        Player existingPlayer = listOfPlayers.stream().filter(
                (currPlayer) -> {
            return currPlayer.id.equals(id);
               }
        ).findFirst().get();

        return null;

    }
    // how to update player, its combination of get and post, kind of recreated
    @PutMapping(path = "/{id}")

    public Player updateSpecificPlayer(@PathVariable String id,@RequestBody Player incomingPlayer){
        Player existingPlayer = getSpecificPlayer(id);
        existingPlayer.name = incomingPlayer.name;
        return existingPlayer;

    }
    //remove player
    @DeleteMapping (path = "/{id}")
    public Player removePlayer(@PathVariable String id ){
        Player existingPlayer = getSpecificPlayer(id);
        listOfPlayers.remove(existingPlayer);
        return existingPlayer;
    }
}
