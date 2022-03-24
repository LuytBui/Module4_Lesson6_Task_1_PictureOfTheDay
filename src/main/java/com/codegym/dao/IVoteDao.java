package com.codegym.dao;

import com.codegym.model.Vote;

import java.util.List;

public interface IVoteDao extends IGeneralDao<Vote>{
    List<Vote> getTodayVotes();
}
