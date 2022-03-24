package com.codegym.dao;

import com.codegym.model.Vote;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
public class VoteDao implements IVoteDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vote> findAll() {
        String qlString = "select v from Vote as v";
        TypedQuery<Vote> query =  entityManager.createQuery(qlString, Vote.class);
        List<Vote> votes = query.getResultList();
        return votes;
    }

    @Override
    public Vote findOne(Long id) {
        if (id == null) return null;
        String qlString = "select v from Vote as v where v.id = :id";
        TypedQuery<Vote> query =  entityManager.createQuery(qlString, Vote.class);
        query.setParameter("id", id);
        Vote vote;
        try {
            vote = query.getSingleResult();
        } catch (NoResultException e){
            vote = null;
        }
        return vote;
    }

    @Override
    public Vote save(Vote vote) {
        vote.setDate(getCurrentDate());
        if (findOne(vote.getId()) == null){
            entityManager.persist(vote);
        } else {
            entityManager.merge(vote);
        }
        return vote;
    }

    @Override
    public Vote remove(Vote vote) {
        if (findOne(vote.getId()) == null){
            return null;
        }
        entityManager.remove(vote);
        return vote;
    }

    @Override
    public Vote remove(Long id) {
        Vote vote = findOne(id);
        if (vote != null){
            entityManager.remove(vote);
        }
        return vote;
    }

    @Override
    public List<Vote> getTodayVotes() {
        String qlString = "select v from Vote as v where v.date = current_date";
        TypedQuery<Vote> query =  entityManager.createQuery(qlString, Vote.class);
        List<Vote> votes = query.getResultList();
        return votes;
    }
    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(now);
    }
}
