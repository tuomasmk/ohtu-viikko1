package ohtuesimerkki;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    Statistics stats;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchingPlayerWorks() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
        assertEquals("EDM", player.getTeam());
        assertEquals(4, player.getGoals());
        assertEquals(12, player.getAssists());
    }
    
    @Test
    public void cantFindPlayerThatDoesNotExist() {
        Player player = stats.search("Virtanen");
        
        assertEquals(null, player);
    }
    
    @Test
    public void findingTeamMembersWork() {
        List<Player> team = stats.team("EDM");
        
        assertEquals("Semenko", team.get(0).getName());
        assertEquals("Kurri", team.get(1).getName());
        assertEquals("Gretzky", team.get(2).getName());
    }
    
    @Test
    public void findingTopScorersReturnsAskedAmountOfPlayers() {
        List<Player> team = stats.topScorers(3);
        
        assertEquals(4, team.size());
    }
    
    @Test
    public void findingTopScorersWorks() {
        List<Player> team = stats.topScorers(3);
        
        assertEquals("Gretzky", team.get(0).getName());
        assertEquals("Lemieux", team.get(1).getName());
        assertEquals("Yzerman", team.get(2).getName());
    }

}
