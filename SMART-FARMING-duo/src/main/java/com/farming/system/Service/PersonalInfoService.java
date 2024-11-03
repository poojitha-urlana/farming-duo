package com.farming.system.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farming.system.Model.PersonalInfo;
import com.farming.system.Repository.PersonalInfoRepository;

import java.util.Optional;

@Service
public class PersonalInfoService {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    public PersonalInfo createPersonalInfo(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    public Optional<PersonalInfo> getPersonalInfo(Long id) {
        return personalInfoRepository.findById(id);
    }

    public Optional<PersonalInfo> updatePersonalInfo(Long id, PersonalInfo personalInfoDetails) {
        return personalInfoRepository.findById(id).map(info -> {
            info.setPhoneNumber(personalInfoDetails.getPhoneNumber());
            info.setDateOfBirth(personalInfoDetails.getDateOfBirth());
            info.setGender(personalInfoDetails.getGender());
            info.setProfilePicture(personalInfoDetails.getProfilePicture());
            info.setPersonalBio(personalInfoDetails.getPersonalBio());
            return personalInfoRepository.save(info);
        });
    }

    public boolean deletePersonalInfo(Long id) {
        if (personalInfoRepository.existsById(id)) {
            personalInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
