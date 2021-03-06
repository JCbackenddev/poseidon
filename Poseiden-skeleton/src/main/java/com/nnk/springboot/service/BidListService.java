package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
	
	private static final Logger log = LoggerFactory.getLogger(BidListService.class);

	@Autowired
	BidListRepository bidRepo;
	
	public BidList saveBid(BidList bid) {
		log.info("SAVING..." + bid);
		
		return bidRepo.save(bid);
	}

	public List<BidList> findAll() {
		return bidRepo.findAll();
	}

	public Optional<BidList> findById(Integer id) {
		
		return bidRepo.findById(id);
	}

	public void delete(BidList bidList) {
		
		bidRepo.delete(bidList);
	}
	
}
