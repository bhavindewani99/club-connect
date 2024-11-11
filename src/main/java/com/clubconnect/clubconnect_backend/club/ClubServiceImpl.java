package com.clubconnect.clubconnect_backend.club;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clubconnect.clubconnect_backend.exception.ResourceNotFoundException;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Club", "id", id));
    }

    @Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}
