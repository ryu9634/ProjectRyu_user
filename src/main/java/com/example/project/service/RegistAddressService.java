package com.example.project.service;

import com.example.project.model.DTO.RegistAddressDTO;
import com.example.project.model.entity.RegistAddress;
import com.example.project.model.enumclass.AddressStatus;
import com.example.project.repository.RegistAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistAddressService {

    @Autowired
    private RegistAddressRepository registAddressRepository;
    @Transactional
    public RegistAddress create(Long userIdx, RegistAddressDTO registAddressDTO){
        RegistAddress registAddress = RegistAddress.builder()
                .userIdx(userIdx)
                .rgaRevaddname(registAddressDTO.getRgaRevaddname())
                .rgaZipcode(registAddressDTO.getRgaZipcode())
                .rgaUserhp(registAddressDTO.getRgaUserhp())
                .rgaAddress1(registAddressDTO.getRgaAddress1())
                .rgaAddress2(registAddressDTO.getRgaAddress2())
                .rgaRevname(registAddressDTO.getRgaRevname())
                .rgaStatus(registAddressDTO.getRgaStatus())
                .build();
        RegistAddress newRegistAddress = registAddressRepository.save(registAddress);
        return newRegistAddress;
    }
    @Transactional
    public List<RegistAddressDTO> getAddressList(Long userIdx) {
        List<RegistAddress> registAddressList = registAddressRepository.findAllByUserIdx(userIdx);
        List<RegistAddressDTO> registAddressDTOList = new ArrayList<>();

        for (RegistAddress registAddress : registAddressList) {
            RegistAddressDTO registAddressDTO = RegistAddressDTO.builder()
                    .rgaIdx(registAddress.getRgaIdx())
                    .rgaRevname(registAddress.getRgaRevname())
                    .rgaRevaddname(registAddress.getRgaRevaddname())
                    .rgaAddress1(registAddress.getRgaAddress1())
                    .rgaAddress2(registAddress.getRgaAddress2())
                    .rgaUserhp(registAddress.getRgaUserhp())
                    .rgaZipcode(registAddress.getRgaZipcode())
                    .userIdx(registAddress.getUserIdx())
                    .rgaStatus(registAddress.getRgaStatus())
                    .build();
            registAddressDTOList.add(registAddressDTO);
        }
        return registAddressDTOList;
    }
    @Transactional
    public RegistAddressDTO read(Long id){
        Optional<RegistAddress> registAddressOptional = registAddressRepository.findById(id);
        RegistAddress registAddress = registAddressOptional.get();
        RegistAddressDTO registAddressDTO = RegistAddressDTO.builder()
                .rgaIdx(registAddress.getRgaIdx())
                .rgaRevname(registAddress.getRgaRevname())
                .rgaRevaddname(registAddress.getRgaRevaddname())
                .rgaAddress1(registAddress.getRgaAddress1())
                .rgaAddress2(registAddress.getRgaAddress2())
                .rgaUserhp(registAddress.getRgaUserhp())
                .rgaZipcode(registAddress.getRgaZipcode())
                .userIdx(registAddress.getUserIdx())
                .rgaStatus(registAddress.getRgaStatus())
                .build();
        return registAddressDTO;
    }
    @Transactional
    public void update(RegistAddressDTO registAddressDTO, Long rgaIdx){
        Optional<RegistAddress> registAddress = registAddressRepository.findByRgaIdx(rgaIdx);

        registAddress.ifPresent(select -> {
            select.setRgaRevname(registAddressDTO.getRgaRevname());
            select.setRgaRevaddname(registAddressDTO.getRgaRevaddname());
            select.setRgaAddress1(registAddressDTO.getRgaAddress1());
            select.setRgaAddress2(registAddressDTO.getRgaAddress2());
            select.setRgaZipcode(registAddressDTO.getRgaZipcode());
            select.setRgaUserhp(registAddressDTO.getRgaUserhp());
            select.setRgaStatus(registAddressDTO.getRgaStatus());

        });
    }
    @Transactional
    public void delete(Long id) {
        registAddressRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(Long userIdx) {
        registAddressRepository.deleteAllByUserIdx(userIdx);
    }


    @Transactional
    public void statusPrime(RegistAddressDTO registAddressDTO){
        Optional<RegistAddress> registAddress = registAddressRepository.findById(registAddressDTO.getRgaIdx());
        registAddress.ifPresent(select -> {
            select.setRgaStatus(AddressStatus.PRIME);
        });
    }

    @Transactional
    public void statusNormal(RegistAddressDTO registAddressDTO){
        Optional<RegistAddress> registAddress = registAddressRepository.findById(registAddressDTO.getRgaIdx());
        registAddress.ifPresent(select -> {
            select.setRgaStatus(AddressStatus.NORMAL);
        });
    }

    @Transactional
    public RegistAddressDTO readPrime(){
        Optional<RegistAddress> registAddressa = registAddressRepository.findByRgaStatus(AddressStatus.PRIME);
        RegistAddress registAddress = registAddressa.get();
        RegistAddressDTO registAddressDTO = RegistAddressDTO.builder()
                .rgaIdx(registAddress.getRgaIdx())
                .rgaRevname(registAddress.getRgaRevname())
                .rgaRevaddname(registAddress.getRgaRevaddname())
                .rgaAddress1(registAddress.getRgaAddress1())
                .rgaAddress2(registAddress.getRgaAddress2())
                .rgaUserhp(registAddress.getRgaUserhp())
                .rgaZipcode(registAddress.getRgaZipcode())
                .userIdx(registAddress.getUserIdx())
                .rgaStatus(registAddress.getRgaStatus())
                .build();
        return registAddressDTO;
    }

    public void deleteBoard(List<String> arr){
        for(int i =0; i< arr.size(); i++){
            String id = arr.get(i);
            registAddressRepository.deleteById((Long.valueOf(id)));
        }
    }

}
