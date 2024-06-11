package org.yg.study.JPAsample.manytomany.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GolferClothesEntityId implements Serializable {

    private GolferEntity golfer;

    private ClothesEntity clothes;


}
