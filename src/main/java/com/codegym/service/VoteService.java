package com.codegym.service;

import com.codegym.dao.IVoteDao;
import com.codegym.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService implements IVoteService {
    @Autowired
    IVoteDao voteDao;

    @Override
    public List<Vote> findAll() {
        return voteDao.findAll();
    }

    @Override
    public Vote findOne(Long id) {
        return voteDao.findOne(id);
    }

    @Override
    public Vote save(Vote Vote) {
        return voteDao.save(Vote);
    }

    @Override
    public Vote remove(Vote Vote) {
        return voteDao.remove(Vote);
    }

    @Override
    public Vote remove(Long id) {
        return voteDao.remove(id);
    }

    @Override
    public List<Vote> getTodayVotes() {
        return voteDao.getTodayVotes();
    }
}
