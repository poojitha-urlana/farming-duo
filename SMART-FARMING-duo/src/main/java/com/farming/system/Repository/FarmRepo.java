package com.farming.system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farming.system.Model.FarmModel;

public interface FarmRepo extends JpaRepository<FarmModel, Long> {

}
