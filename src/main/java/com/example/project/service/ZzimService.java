package com.example.project.service;

import com.example.project.model.DTO.ZzimDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Zzim;
import com.example.project.repository.CouponRepository;
import com.example.project.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZzimService {

    @Autowired
    private ZzimRepository zzimRepository;

    @Transactional
    public Zzim create(Long userIdx, ZzimDTO zzimDTO){
        Zzim zzim = Zzim.builder()
                .userIdx(userIdx)
                .goods(Goods.builder()
                        .gdIdx(zzimDTO.getGoods().getGdIdx()).build())
                .build();
        Zzim newZzim = zzimRepository.save(zzim);
        return newZzim;
    }

    public Page<Zzim> zzim_list(Long userIdx , Pageable pageable){
        return zzimRepository.findAllByUserIdx(userIdx , pageable);
    }

    @Transactional
    public List<ZzimDTO> getZzimList(Long userIdx){
        List<Zzim> zzimList = zzimRepository.findAllByUserIdx(userIdx);
        List<ZzimDTO> zzimDTOList = new ArrayList<>();

        for(Zzim zzim : zzimList){
            ZzimDTO zzimDTO = ZzimDTO.builder()
                    .zzIdx(zzim.getZzIdx())
                    .goods(Goods.builder()
                            .gdIdx(zzim.getGoods().getGdIdx())
                            .gdName(zzim.getGoods().getGdName())
                            .gdPrice(zzim.getGoods().getGdPrice())
                            .gdSaleprice(zzim.getGoods().getGdSaleprice())
                            .gdImg(zzim.getGoods().getGdImg())
                            .build())
                    .build();
            zzimDTOList.add(zzimDTO);
        }
        return zzimDTOList;
    }

    @Transactional
    public void delete(Long id){
        zzimRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(Long userIdx){
        zzimRepository.deleteAllByUserIdx(userIdx);
    }

    @Transactional
    public void deleteBoard(List<String> arr){
        for(int i =0; i< arr.size(); i++){
            String id = arr.get(i);
            zzimRepository.deleteById((Long.valueOf(id)));
        }
    }
}
