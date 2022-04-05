package com.example.project.model.DTO;

import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="tb_review")
public class ReviewDTO {
    private Long rvIdx;
    private Long userIdx;
    private LocalDateTime rvRegdate;
    private String rvTitle;
    private String rvContent;
    private Integer rvStar;
    private String rvImg;
    private Goods goods;
}
