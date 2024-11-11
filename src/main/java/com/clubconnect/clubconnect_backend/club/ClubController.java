package com.clubconnect.clubconnect_backend.club;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    // Endpoint to create a new club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club createdClub = clubService.createClub(club);
        return ResponseEntity.ok(createdClub);
    }

    // Endpoint to get a club by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.ok(club);
    }

    // Endpoint to get a list of all clubs
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return ResponseEntity.ok(clubs);
    }
}
