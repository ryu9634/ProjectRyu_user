package com.example.project.controller.api;

import com.example.project.model.DTO.RegistAddressDTO;
import com.example.project.service.RegistAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regist_address")
public class RegistAddressController {
    private final RegistAddressService registAddressService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody RegistAddressDTO registAddressDTO){
        registAddressService.create(userIdx, registAddressDTO);
    }

    @GetMapping("{id}")
    public RegistAddressDTO read(@PathVariable Long id) {
        return registAddressService.read(id);
    }

    @GetMapping("/list/{userIdx}")
    public List<RegistAddressDTO> list(@PathVariable Long userIdx){
        List<RegistAddressDTO> registAddressDTOList = registAddressService.getAddressList(userIdx);
        return registAddressDTOList;
    }

    @PostMapping("/update/{rgaIdx}")
    private void update(@RequestBody RegistAddressDTO registAddressDTO, @PathVariable Long rgaIdx){
        registAddressService.update(registAddressDTO, rgaIdx);
    }

    @DeleteMapping("/delete/{id}")
    private void delete(@PathVariable Long id){
        registAddressService.delete(id);
    }

    @DeleteMapping("{userIdx}")
    private void deleteAll(@PathVariable Long userIdx){
        registAddressService.deleteAll(userIdx);
    }

    @PutMapping("/prime")
    private void statusPrime(@RequestBody RegistAddressDTO registAddressDTO){
        registAddressService.statusPrime(registAddressDTO);
    }

    @PutMapping("/normal")
    private void statusNormal(@RequestBody RegistAddressDTO registAddressDTO){
        registAddressService.statusNormal(registAddressDTO);
    }

    @PostMapping("/deleteAdd/delete")
    public List<String> deleteSubmit(@RequestBody List<String> arr){
        registAddressService.deleteBoard(arr);
        return arr;
    }
}
