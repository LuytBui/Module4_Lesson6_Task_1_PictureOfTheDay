package com.codegym.service;

import com.codegym.model.Vote;

import java.util.List;

public interface IVoteService extends IGeneralService<Vote> {
    List<Vote> getTodayVotes();
}
