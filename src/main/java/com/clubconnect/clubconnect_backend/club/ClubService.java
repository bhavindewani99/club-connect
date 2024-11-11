package com.clubconnect.clubconnect_backend.club;

import java.util.List;

public interface ClubService {
    Club createClub(Club club);
    Club getClubById(Long id);
    List<Club> getAllClubs();
}
