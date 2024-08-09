package com.revature.backend.service;

import java.util.List;

import com.revature.backend.model.Associate;
import com.revature.backend.repository.AssociateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociateService {
  private final AssociateRepository associateRepo;

  public AssociateService(AssociateRepository associateRepo) {
    this.associateRepo = associateRepo;
  }

  public List<Associate> getAllAssociates() {
      return null;
  }

  public List<Associate> saveAssociates(List<Associate> aList) {
      // call the appropriate Repo method to save (aka insert) associates into DB
      List<Associate> list = associateRepo.saveAll(aList);
      return list;
  }

  public Associate getAssociateById(int id) {
    // TODO call the appropriate Repo method to update an associate into DB
    return associateRepo.findById(id);
  }

  public void updateAssociate(Associate associate) {
    // TODO call the appropriate Repo method to update an associate into DB
    associateRepo.save(associate);
  }
}
