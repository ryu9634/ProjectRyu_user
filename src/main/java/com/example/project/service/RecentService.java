package com.example.project.service;

import com.example.project.model.DTO.RecentDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Order;
import com.example.project.model.entity.Recent;
import com.example.project.model.entity.User;
import com.example.project.repository.RecentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecentService {

    @Autowired
    private RecentRepository recentRepository;

    @Transactional
    public Recent create(Long userIdx, RecentDTO recentDTO) {
        Recent recent = Recent.builder()
                .userIdx(userIdx)
                .goods(Goods.builder()
                        .gdIdx(recentDTO.getGoods().getGdIdx())
                        .build())
                .build();
        Recent newRecent = recentRepository.save(recent);
        return newRecent;
    }

    @Transactional
    public List<RecentDTO> getRecentList(Long userIdx){
        List<Recent> recentList = recentRepository.findAllByUserIdx(userIdx);
        List<RecentDTO> recentDTOList = new ArrayList<>();

        for(Recent recent : recentList){
            RecentDTO recentDTO = RecentDTO.builder()
                    .rcIdx(recent.getRcIdx())
                    .goods(Goods.builder()
                            .gdIdx(recent.getGoods().getGdIdx())
                            .gdName(recent.getGoods().getGdName())
                            .gdPrice(recent.getGoods().getGdPrice())
                            .gdSaleprice(recent.getGoods().getGdSaleprice())
                            .gdImg(recent.getGoods().getGdImg())
                            .build())
                    .build();
            recentDTOList.add(recentDTO);
        }
        return recentDTOList;
    }

    public Page<Recent> recents_page(Long userIdx , Pageable pageable){
        return recentRepository.findAllByUserIdx(userIdx , pageable);
    }

    @Transactional
    public void delete(Long id) {
        recentRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(Long userIdx){
        recentRepository.deleteAllByUserIdx(userIdx);
    }

    @Transactional
    public void deleteBoard(List<String> arr){
        for(int i =0; i< arr.size(); i++){
            String id = arr.get(i);
            recentRepository.deleteById((Long.valueOf(id)));
        }
    }
}
