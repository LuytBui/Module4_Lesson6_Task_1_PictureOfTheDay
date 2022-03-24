package com.codegym.controller;

import com.codegym.model.Vote;
import com.codegym.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VoteController {
    @Autowired
    IVoteService voteService;

    @GetMapping("/home")
    public ModelAndView viewHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("vote", new Vote());

        List<Vote> todayVotes = voteService.getTodayVotes();
        modelAndView.addObject("todayVotes", todayVotes);
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView postVoteAndRefresh(@ModelAttribute Vote vote) {
        voteService.save(vote);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping ("/like/{voteId}")
    public ModelAndView likeVote(@PathVariable Long voteId){
        Vote vote = voteService.findOne(voteId);
        if (vote != null) {
            int likeCount = vote.getLikeCount();
            likeCount ++;
            vote.setLikeCount(likeCount);
            voteService.save(vote);
        }
        return new ModelAndView("redirect:/home");
    }

}
